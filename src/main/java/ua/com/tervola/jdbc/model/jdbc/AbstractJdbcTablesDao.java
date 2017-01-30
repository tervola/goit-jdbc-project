package ua.com.tervola.jdbc.model.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by user on 1/30/2017.
 */
public abstract class AbstractJdbcTablesDao {

    private static Logger LOGGER = LogManager.getLogger(AbstractJdbcTablesDao.class);

    private ComboPooledDataSource dataSource;

    Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void setDataSource(ComboPooledDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Transactional
    void removeFromTable(int index, String tableName){
        try {
            StringBuilder sqlCommand = new StringBuilder();
            sqlCommand.append("DELETE FROM ? WHERE dish_di = ?");

            PreparedStatement preparedStatement = getConnection().prepareStatement(sqlCommand.toString());
            preparedStatement.setString(1,tableName);
            preparedStatement.setInt(2,index);

            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e){
            LOGGER.error(String.format("Error, while updating %s table",tableName));
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
