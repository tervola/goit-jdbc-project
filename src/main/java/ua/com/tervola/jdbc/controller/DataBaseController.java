package ua.com.tervola.jdbc.controller;

import ua.com.tervola.jdbc.model.DatabaseDao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by user on 11/20/2016.
 */
public class DataBaseController {

    private DatabaseDao databaseDao;

    public void setDatabaseDao(DatabaseDao databaseDao) {
        this.databaseDao = databaseDao;
    }

    public List<String> getAllTables() throws SQLException {
        return databaseDao.getAllTables();
    }
}
