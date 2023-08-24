package com.urise.webapp.sql;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.StorageException;
import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public record SqlHelper(ConnectionFactory connectionFactory) {

    public <T> T execute(String sql, SqlExecute<T> sqlExecute) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            return sqlExecute.execute(ps);
        } catch (SQLException e) {
            if (e instanceof PSQLException) {
                if (e.getSQLState().equals("23505")) {
                    throw new ExistStorageException(null);
                }
            }
            throw new StorageException(e);
        }
    }

}
