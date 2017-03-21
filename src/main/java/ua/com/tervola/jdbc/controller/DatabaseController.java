package ua.com.tervola.jdbc.controller;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import ua.com.tervola.jdbc.model.DatabaseDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by user on 11/20/2016.
 */

public class DatabaseController {

    private DatabaseDao databaseDao;

    public DatabaseController(DatabaseDao databaseDao) {
        this.databaseDao = databaseDao;
    }

    public List<String> getAllTables() throws SQLException {
        return this.databaseDao.getAllTables();
    }

    public Connection getConnection() throws SQLException {
        return this.databaseDao.getDataSource().getConnection();
    }

    public ComboPooledDataSource getManager() {
        return this.databaseDao.getDataSource();
    }

    public List<String> getFieldsInTable(String table) {
        return this.databaseDao.getFieldsInTable(table);
    }

    public void updateTable(String sqlCommand) {
        this.databaseDao.updateTable(sqlCommand);
    }
}
