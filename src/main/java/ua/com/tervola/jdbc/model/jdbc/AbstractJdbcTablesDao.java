package ua.com.tervola.jdbc.model.jdbc;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import ua.com.tervola.jdbc.controller.DatabaseController;
import ua.com.tervola.jdbc.ProjectTables;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 1/30/2017.
 */
public class AbstractJdbcTablesDao {

    private static Logger LOGGER = LogManager.getLogger(AbstractJdbcTablesDao.class);

    private DatabaseController databaseController;
    protected static String CONDITION_GT = ">=";
    protected static String CONDITION_EQ = "=";

    public AbstractJdbcTablesDao(DatabaseController databaseController) {
        this.databaseController = databaseController;
    }

    public DatabaseController getDatabaseController() {
        return this.databaseController;
    }

    @Transactional
    protected void removeFromTable(int index, ProjectTables tableName, String field) {
        try {
            String sqlCommand = String.format("DELETE FROM %s WHERE %s = %s", tableName, field, index);

            PreparedStatement preparedStatement = getConnection().prepareStatement(sqlCommand);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            LOGGER.error(String.format("Error, while updating %s table", tableName));
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private Connection getConnection() throws SQLException {
        return this.databaseController.getConnection();
    }

    @Transactional
    protected ResultSet findInTable(String fieldName, ProjectTables tableName, String idField, String condition) {
        try {
            String sqlCommand = String.format("SELECT * FROM public.%s WHERE %s %s %s", tableName, fieldName,condition,idField);

            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand);
            return preparedStatement.executeQuery();

        } catch (SQLException e) {
            LOGGER.error("Error occured during connection to " + this.databaseController.getManager().getJdbcUrl() + " User is:" + this.databaseController.getManager().getUser() + " Password is: " + this.databaseController.getManager().getPassword());
            throw new RuntimeException(e);
        }
    }

    @Transactional
    protected ResultSet findIntabledAllRecords(ProjectTables tableName) {

        try {
            Statement statement = getConnection().createStatement();
            String sqlCommand = String.format("SELECT * FROM %s", tableName);
            return statement.executeQuery(sqlCommand);
        } catch (SQLException e) {
            LOGGER.error("Error occured during connection to " + this.databaseController.getManager().getJdbcUrl());
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public List<Integer> getIndexesFromTable(String field, ProjectTables table) throws SQLException {
        List<Integer> result = new ArrayList<>();
        try {
            Statement statement = getConnection().createStatement();
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

    @Transactional
    protected boolean updateTable(int id, ProjectTables tableName, String dataSet, String fieldId) {
        boolean success = false;
        try {
            Statement statement = getConnection().createStatement();
            String sqlCommand = String.format("UPDATE %s SET %s WHERE %s = %s", tableName, dataSet, fieldId, id);

            PreparedStatement preparedStatement = getConnection().prepareStatement(sqlCommand);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            success = true;

        } catch (SQLException e) {
            LOGGER.error("Error occured during updating table " + this.databaseController.getManager().getJdbcUrl());
            throw new RuntimeException(e);
        }

        return success;
    }
}
