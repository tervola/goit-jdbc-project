package ua.com.tervola.jdbc.model;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by user on 11/18/2016.
 */
public interface DatabaseDao {

    boolean isDisconnected() throws SQLException;

    List<String> getAllTables() throws SQLException;

    ComboPooledDataSource getDataSource();

    void setDataSource(ComboPooledDataSource dataSource);

    List<String> getFieldsInTable(String table);
}
