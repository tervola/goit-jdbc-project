package ua.com.tervola.jdbc.model.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import ua.com.tervola.jdbc.model.DatabaseDao;
import ua.com.tervola.jdbc.ProjectTables;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/18/2016.
 */
@Component
public class JdbcDatabaseDao implements DatabaseDao {

    private static Logger LOGGER = LogManager.getLogger(JdbcDatabaseDao.class);

    private static String FIELD_TABLE_SCHEMA = "table_schema";
    private static String FIELD_TABLE_NAME = "table_name";
    private static String FIELD_COLUMN_NAME = "column_name";
    private static String FIELD_CONDITION_PUBLIC = "public";

    private ComboPooledDataSource dataSource;

    @Override
    public boolean isDisconnected() {
        try {
            return dataSource.getConnection().isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<String> getAllTables()  {
        List<String> result = new ArrayList<>();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            String sqlCommand = String.format("SELECT %s FROM %s WHERE %s = '%s'",
                    FIELD_TABLE_NAME,
                    ProjectTables.SYSTEM_TABLE_INFORMATION_SCHEMA,
                    FIELD_TABLE_SCHEMA,
                    FIELD_CONDITION_PUBLIC);
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while(resultSet.next()){
                result.add(resultSet.getString("table_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(result.toString());
        return result;
    }

    public void setDataSource(ComboPooledDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public ComboPooledDataSource getDataSource() {
        return dataSource;
    }

    @Override
    public List<String> getFieldsInTable(String table) {
        List<String> result = new ArrayList<>();
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            String sqlCommand = String.format("SELECT %s FROM %s WHERE %s = '%s' AND %s = '%s'",
                    FIELD_COLUMN_NAME,
                    ProjectTables.SYSTEM_COLUMN_INFORMATION_SCHEMA,
                    FIELD_TABLE_NAME,
                    table,
                    FIELD_TABLE_SCHEMA,
                    FIELD_CONDITION_PUBLIC);
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while(resultSet.next()){
                result.add(resultSet.getString(FIELD_COLUMN_NAME));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(result.toString());
        return result;
    }
}
