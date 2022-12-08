package ru.kpfu.itis.repositories.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ru.kpfu.itis.dao.EditAdvertDao;
import ru.kpfu.itis.models.Advert;
import ru.kpfu.itis.models.User;
import ru.kpfu.itis.repositories.AdvertRepository;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.*;

public class AdvertRepositoryImpl implements AdvertRepository {

    //language=SQL
    private static final String SQL_ADD_AUTHOR = "" +
            "update adverts set author_id=?, city=?, address=? where id=?";
    //language=SQL
    private static final String SQL_GET_ALL_ACTIVE_ADVERTS = "" +
            "select * from adverts where status=true";
    //language=SQL
    private static final String SQL_SELECT_ADVERT_BY_ID = "" +
            "select * from adverts where id=?";
    //language=SQL
    private static final String SQL_SOLD_ADVERT = "" +
            "update adverts set status=?, sales_end_date=? where id=?";
    //language=SQL
    private static final String SQL_GET_ALL_SOLD_ADVERTS_USER = """
            select * from adverts
            inner join sold_items
            on adverts.id = sold_items.advert_id
            where account_id = ?;""";

    //language=SQL
    private static final String SQL_GET_ALL_FAVOURITE_ADVERTS_USER = """
            select * from adverts
            inner join favourites
            on adverts.id = favourites.advert_id
            where account_id = ?;""";
    //language=SQL
    private static final String SQL_DELETE_FAVORITE_ADVERT = "" +
            "delete from favourites where account_id=? and advert_id=?";
    //language=SQL
    private static final String SQL_SELECT_ACTIVE_ADVERTS_USER = "" +
            "select * from adverts where author_id=? and status=true";
    //language=SQL
    private static final String SQL_SEARCH_QUERY = "select * from adverts where " +
            "((adverts.name ilike ? or adverts.description ilike ?) and status=true)";
    //language=SQL
    private static final String SQL_FIND_BY_CITY = "select * from adverts where city = ? and status=true";
    //language=SQL
    private static final String SQL_GET_COUNT_FAVOURITE = "select count(*) from favourites where advert_id=?";
    //language=SQL
    private static final String SQL_UPDATE_ADVERT = "update adverts set name=?, description=?, price=? where id=?";
    //language=SQL
    private static final String SQL_ADVERT_UPDATE_STATUS = "update adverts set status=true, sales_end_date=null where id = ?";
    //language=SQL
    private static final String SQL_DELETE_SOLD_ITEM = "delete from sold_items where advert_id=?";
    private static final RowMapper<Advert> advertMapper = (row, rowNumber) -> Advert.builder()
            .id(row.getLong("id"))
            .name(row.getString("name"))
            .description(row.getString("description"))
            .price(row.getInt("price"))
            .authorId(row.getLong("author_id"))
            .category(row.getString("category"))
            .city(row.getString("city"))
            .address(row.getString("address"))
            .salesStartDate(row.getString("sales_start_date"))
            .dateOfSale(row.getString("sales_end_date"))
            .status(row.getBoolean("status"))
            .build();



    private final JdbcTemplate jdbcTemplate;

    public AdvertRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void addAdvert(Advert advert) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String nowDate = simpleDateFormat.format(date);

        Map<String, Object> paramsAsMap = new HashMap<>();

        paramsAsMap.put("name", advert.getName());
        paramsAsMap.put("description", advert.getDescription());
        paramsAsMap.put("price", advert.getPrice());
        paramsAsMap.put("sales_start_date", nowDate);
        paramsAsMap.put("category", advert.getCategory());
        paramsAsMap.put("author_id", advert.getAuthorId());
        paramsAsMap.put("city", advert.getCity());
        paramsAsMap.put("address", advert.getAddress());
        paramsAsMap.put("status", true);

        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);

        Long id = insert.withTableName("adverts").usingGeneratedKeyColumns("id")
                .executeAndReturnKey(new MapSqlParameterSource(paramsAsMap))
                .longValue();

        advert.setId(id);
    }

    @Override
    public void addSoldAdvert(Advert advert) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String nowDate = simpleDateFormat.format(date);
        Map<String, Object> paramsAsMap = new HashMap<>();

        paramsAsMap.put("account_id", advert.getAuthorId());
        paramsAsMap.put("advert_id", advert.getId());

        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);

        insert.withTableName("sold_items").usingGeneratedKeyColumns("id")
                .executeAndReturnKey(new MapSqlParameterSource(paramsAsMap))
                .longValue();

        advert.setStatus(false);
        jdbcTemplate.update(SQL_SOLD_ADVERT, advert.getStatus(), nowDate, advert.getId());
    }

    @Override
    public List<Advert> getAllSoldAdvertsUser(User user) {
        return jdbcTemplate.query(SQL_GET_ALL_SOLD_ADVERTS_USER, advertMapper, user.getId());
    }

    @Override
    public void addAdvertToFavourite(User user, Advert advert) {
        Map<String, Object> paramsAsMap = new HashMap<>();

        paramsAsMap.put("account_id", user.getId());
        paramsAsMap.put("advert_id", advert.getId());

        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);

        insert.withTableName("favourites").usingGeneratedKeyColumns("id")
                .executeAndReturnKey(new MapSqlParameterSource(paramsAsMap))
                .longValue();
    }

    @Override
    public void removeAdvertFromFavourite(Advert advert, User user) {
        jdbcTemplate.update(SQL_DELETE_FAVORITE_ADVERT, user.getId(), advert.getId());
    }

    @Override
    public List<Advert> findAllAdvert() {
        return jdbcTemplate.query(SQL_GET_ALL_ACTIVE_ADVERTS, advertMapper);
    }

    @Override
    public List<Advert> getAllActiveAdvertsUser(User user) {
        return jdbcTemplate.query(SQL_SELECT_ACTIVE_ADVERTS_USER, advertMapper, user.getId());
    }

    @Override
    public List<Advert> getAllFavouriteAdvertsUser(User user) {
        return jdbcTemplate.query(SQL_GET_ALL_FAVOURITE_ADVERTS_USER, advertMapper, user.getId());
    }

    @Override
    public Advert getAdvertById(Long id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_ADVERT_BY_ID, advertMapper, id);
    }

    @Override
    public List<Advert> findAdvertByRequest(String searchInput) {
        return jdbcTemplate.query(SQL_SEARCH_QUERY, advertMapper, "%" + searchInput + "%", "%" + searchInput + "%");
    }

    @Override
    public List<Advert> findAdvertByCity(String city) {
        return jdbcTemplate.query(SQL_FIND_BY_CITY, advertMapper, city);
    }

    @Override
    public Integer getCountFavouriteAdvert(Advert advert) {
        return jdbcTemplate.queryForObject(SQL_GET_COUNT_FAVOURITE, Integer.class, advert.getId());
    }

    @Override
    public void updateAdvertInformation(Advert advert) {
        jdbcTemplate.update(SQL_UPDATE_ADVERT, advert.getName(), advert.getDescription(), advert.getPrice(), advert.getId());
    }

    @Override
    public void updateSoldItem(Advert advert) {
        jdbcTemplate.update(SQL_ADVERT_UPDATE_STATUS, advert.getId());
        jdbcTemplate.update(SQL_DELETE_SOLD_ITEM, advert.getId());
        advert.setStatus(true);
    }
}
