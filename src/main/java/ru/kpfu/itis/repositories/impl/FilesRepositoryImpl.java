package ru.kpfu.itis.repositories.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ru.kpfu.itis.models.FileInfo;
import ru.kpfu.itis.models.User;
import ru.kpfu.itis.repositories.FilesRepository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class FilesRepositoryImpl implements FilesRepository {
    private final JdbcTemplate jdbcTemplate;

    //language=SQL
    private final static String SQL_GET_FILE_INFO_BY_USER_ID_AVATAR = "select * from file_info where load_account = ? and type='avatar'";
    //language=SQL
    private final static String SQL_UPDATE_TYPE_AFTER_DELETE = "update file_info set type = 'unused' where load_account=? and type='avatar'";
    //language=SQL
    private final static String SQL_UPDATE_TYPE_TEMP_AVATAR_AFTER_DELETE = "update file_info set type = 'avatar' where load_account=? and type='tempAvatar'";
    //language=SQL
    private final static String SQL_DELETE_AVATAR_BY_ID = "delete from avatars where account_id = ?";

    public FilesRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final static RowMapper<FileInfo> fileInfoRowMapper = (row, rowNumber) -> FileInfo.builder()
            .id(row.getLong("id"))
            .originalFileName(row.getString("original_file_name"))
            .storageFileName(row.getString("storage_file_name"))
            .size(row.getLong("size"))
            .mimeType(row.getString("mime_type"))
            .type(row.getString("type"))
            .loadAccount(row.getLong("load_account"))
            .build();

    @Override
    public void save(FileInfo fileInfo) {
        Map<String, Object> params = new HashMap<>();
        params.put("original_file_name", fileInfo.getOriginalFileName());
        params.put("storage_file_name", fileInfo.getStorageFileName());
        params.put("size", fileInfo.getSize());
        params.put("mime_type", fileInfo.getMimeType());
        params.put("type", fileInfo.getType());
        params.put("load_account", fileInfo.getLoadAccount());

        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);

        Long id = insert.withTableName("file_info")
                .usingGeneratedKeyColumns("id")
                .executeAndReturnKey(new MapSqlParameterSource(params)).longValue();

        fileInfo.setId(id);
    }

    @Override
    public void addAvatarUser(FileInfo fileInfo, User user) {
        Map<String, Object> paramsAsMap = new HashMap<>();

        paramsAsMap.put("account_id", user.getId());
        paramsAsMap.put("avatar_id", fileInfo.getId());

        SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);

        insert.withTableName("avatars").usingGeneratedKeyColumns("id")
                .executeAndReturnKey(new MapSqlParameterSource(paramsAsMap))
                .longValue();
    }

    @Override
    public FileInfo getFileInfoAvatarByUserId(User user) {
        return jdbcTemplate.queryForObject(SQL_GET_FILE_INFO_BY_USER_ID_AVATAR, fileInfoRowMapper, user.getId());
    }

    @Override
    public void deleteAvatarUser(User user) {
        jdbcTemplate.update(SQL_DELETE_AVATAR_BY_ID, user.getId());
        jdbcTemplate.update(SQL_UPDATE_TYPE_AFTER_DELETE, user.getId());
        jdbcTemplate.update(SQL_UPDATE_TYPE_TEMP_AVATAR_AFTER_DELETE, user.getId());
    }
}
