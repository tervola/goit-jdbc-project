package ua.com.tervola.jdbc;

import ua.com.tervola.jdbc.model.jdbc.JdbcEmployeeDao;

/**
 * Created by user on 11/3/2016.
 */
public class MainClass {
    public static void main(String[] args) {
        JdbcEmployeeDao jdbcEmployeeDao = new JdbcEmployeeDao();
        jdbcEmployeeDao.sample();
    }
}
