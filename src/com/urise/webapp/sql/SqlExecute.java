package com.urise.webapp.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
@FunctionalInterface
public interface SqlExecute <T> {
    T execute(PreparedStatement ps) throws SQLException;
}
