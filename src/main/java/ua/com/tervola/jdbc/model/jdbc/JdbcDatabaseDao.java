package ua.com.tervola.jdbc.model.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.com.tervola.jdbc.model.DatabaseDao;
import ua.com.tervola.jdbc.model.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/18/2016.
 */
public class JdbcDatabaseDao implements DatabaseDao {

    private static Logger LOGGER = LogManager.getLogger(JdbcDatabaseDao.class);
    private ComboPooledDataSource dataSource;

    @Override
    public boolean isConnected() {
        try {
            return !dataSource.getConnection().isClosed();
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
            ResultSet resultSet = statement.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema = 'public';");
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
}
