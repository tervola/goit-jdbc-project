package ua.com.tervola.jdbc.model.jdbc;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.tervola.jdbc.model.Employee;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * Created by user on 11/4/2016.
 */
public class JdbcEmployeeDao {
    private DataSource dataSource;
    private Logger LOGGER = LogManager.getLogger(JdbcEmployeeDao.class);

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
     public Employee load(int id){
        try {
            Connection connection = dataSource.getConnection();
            String sqlCommand = "SELECT * FROM employee WHERE ";
            PreparedStatement statement = connection.prepareStatement(sqlCommand);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Employee> findAll(){
        try {
            Connection connection = dataSource.getConnection();
            String sqlCommand = "SELECT * FROM employee";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlCommand);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
