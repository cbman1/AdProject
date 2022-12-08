package ru.kpfu.itis.repositories.impl;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ru.kpfu.itis.models.FileInfo;
import ru.kpfu.itis.models.User;
import ru.kpfu.itis.repositories.UserRepository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    //language=SQL
    private static final String SQL_SELECT_BY_EMAIL = "" +
            "select * from accounts where email = ?";
    //language=SQL
    private static final String SQL_UPDATE_USER_INFORMATION = "" +
            "update accounts set city=?, address=? where id = ?";
    //language=SQL
    private static final String SQL_SELECT_USER_BY_ID = "" +
            "select * from accounts where id=?";
    //language=SQL
    private static final String SQL_SET_AVATAR_UUID = "" +
            "update accounts set avatar_uuid = ? where id = ?";
    //language=SQL
    private static final String SQL_GET_AVATAR_UUID = "" +
            "select avatar_uuid from accounts where id = ?;";


    private static final RowMapper<User> userMapper = (row, rowNumber) -> User.builder()
            .id(row.getLong("id"))
            .firstName(row.getString("first_name"))
            .lastName(row.getString("last_name"))
            .email(row.getString("email"))
            .password(row.getString("password"))
            .phoneNumber(row.getLong("phone_number"))
            .city(row.getString("city"))
            .address(row.getString("address"))
            .build();

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(User user) {
        Map<String, Object> paramsAsMap = new HashMap<>();
        paramsAsMap.put("first_name", user.getFirstName());
        paramsAsMap.put("last_name", user.getLastName());
        paramsAsMap.put("password", user.getPassword());
        paramsAsMap.put("email", user.getEmail());
        paramsAsMap.put("phone_number", user.getPhoneNumber());
        paramsAsMap.put("avatar_uuid", "default.png");

        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);

        Long id = insert.withTableName("accounts").usingGeneratedKeyColumns("id")
                .executeAndReturnKey(new MapSqlParameterSource(paramsAsMap))
                .longValue();

        user.setId(id);
    }

    @Override
    public Optional<User> findOneByEmail(String email) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_SELECT_BY_EMAIL, userMapper, email));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void updateInformationUser(User user, String city, String address) {
        jdbcTemplate.update(SQL_UPDATE_USER_INFORMATION, city, address, user.getId());
    }

    @Override
    public User getUserById(Long id) {
        return jdbcTemplate.queryForObject(SQL_SELECT_USER_BY_ID, userMapper, id);
    }

    @Override
    public void newAvatarUuid(FileInfo fileInfo, User user) {
        jdbcTemplate.update(SQL_SET_AVATAR_UUID, fileInfo.getStorageFileName(), user.getId());
    }

    @Override
    public String getAvatarUuidById(User user) {
        return jdbcTemplate.queryForObject(SQL_GET_AVATAR_UUID, String.class, user.getId());
    }
}
