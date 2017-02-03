package ua.com.tervola.jdbc.model.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.com.tervola.jdbc.controller.DatabaseController;
import ua.com.tervola.jdbc.model.Dish;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 1/30/2017.
 */
public class AbstractJdbcTablesDao {

    private static Logger LOGGER = LogManager.getLogger(AbstractJdbcTablesDao.class);

    private DatabaseController databaseController;

    public AbstractJdbcTablesDao(DatabaseController databaseController) {
        this.databaseController = databaseController;
    }

    public DatabaseController getDatabaseController() {
        return this.databaseController;
    }

//    @Transactional
    protected void removeFromTable(int index, String tableName, String field) {
        try {
            String sqlCommand = String.format("DELETE FROM %s WHERE %s = %s",tableName,field, index);

            PreparedStatement preparedStatement = this.databaseController.getConnection().prepareStatement(sqlCommand);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            LOGGER.error(String.format("Error, while updating %s table", tableName));
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

//    @Transactional
    protected ResultSet findInTable(String fieldName, String tableName, String idField) {
        try {
            Connection connection = this.databaseController.getConnection();
            String sqlCommand = String.format("SELECT * FROM %s WHERE %s = %s", tableName, fieldName, idField);
            PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
            return preparedStatement.executeQuery();

        } catch (SQLException e) {
            LOGGER.error("Error occured during connection to " + this.databaseController.getManager().getJdbcUrl() + " User is:" + this.databaseController.getManager().getUser() + " Password is: " + this.databaseController.getManager().getPassword());
            throw new RuntimeException(e);
        }
    }

//    @Transactional
    protected ResultSet findIntabledAllRecords(String tableName) {

        try {
            Connection connection = this.databaseController.getConnection();
            Statement statement = connection.createStatement();
            String sqlCommand = String.format("SELECT * FROM %s", tableName);
            return statement.executeQuery(sqlCommand);
        } catch (SQLException e) {
            LOGGER.error("Error occured during connection to " + this.databaseController.getManager().getJdbcUrl());
            throw new RuntimeException(e);
        }
    }

//    @Transactional
    public List<Integer> getIndexesFromTable(String field, String table) throws SQLException {
        List<Integer> result = new ArrayList<>();
        try {
            Connection connection = this.databaseController.getConnection();
            Statement statement = connection.createStatement();
            String sqlCommand = String.format("SELECT %s FROM %s", field, table);
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while (resultSet.next()) {
                int employee_id = resultSet.getInt(field);
                result.add(employee_id);
            }

        } catch (SQLException e) {
            LOGGER.error("Error occured during getting indexes " + this.databaseController.getManager().getJdbcUrl());
            throw new RuntimeException(e);
        }

        return result;
    }
}
