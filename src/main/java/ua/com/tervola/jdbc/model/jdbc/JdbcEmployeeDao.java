package ua.com.tervola.jdbc.model.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.tervola.jdbc.model.Employee;
import ua.com.tervola.jdbc.model.EmployeeDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/4/2016.
 */
public class JdbcEmployeeDao extends  AbstractJdbcTablesDao implements EmployeeDao {
    private static Logger LOGGER = LogManager.getLogger(JdbcEmployeeDao.class);

    private ComboPooledDataSource dataSource;

    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public List<Employee> findAll(){

        List<Employee> result = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employee");
            while(resultSet.next()){
                Employee employee = createEmployee(resultSet);
                result.add(employee);
            }
        } catch (SQLException e) {
            LOGGER.error("Error occured during connection to " + dataSource.getJdbcUrl());
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Employee findById(int id){
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee WHERE employee_id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return createEmployee(resultSet);
            } else {
                throw new RuntimeException("Cannot find employee with id " + id);
            }

        } catch (SQLException e){
            LOGGER.error("Error occured during connection to " + dataSource.getJdbcUrl() + " User is:" + dataSource.getUser() + " Password is: " + dataSource.getPassword());
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public void addEmployee(Employee employee){
        try {
            Connection connection = dataSource.getConnection();
            StringBuilder sqlCommand = new StringBuilder();
            sqlCommand.append("INSERT INTO employee (employee_id,surname,name,birthday,phone,position,salary )");
            sqlCommand.append("VALUES (?,?,?,?,?,?,?)");

            PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand.toString());
            preparedStatement.setInt(1,employee.getId());
            preparedStatement.setString(2,employee.getSurName());
            preparedStatement.setString(3,employee.getName());
            preparedStatement.setDate(4, Date.valueOf(employee.getBirthday()));
            preparedStatement.setString(5,employee.getPhone());
            preparedStatement.setString(6,employee.getPosition());
            preparedStatement.setFloat(7,employee.getSalary());

            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e){
            LOGGER.error("Error, while updating EMPLOYEE table");
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public List<Integer> getIndexes() throws SQLException {
        List<Integer> result = new ArrayList<>();
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select employee_id from employee");
            while(resultSet.next()){
                int employee_id = resultSet.getInt("employee_id");
                result.add(employee_id);
            }
        } catch (SQLException e) {
            LOGGER.error("Error occured during getting indexes " + dataSource.getJdbcUrl());
            throw new RuntimeException(e);
        }
        return result;

    }

    private Employee createEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("employee_id"));
        employee.setSurName(resultSet.getString("surname"));
        employee.setName(resultSet.getString("name"));
        employee.setPhone(resultSet.getString("phone"));
        employee.setBirthday(resultSet.getString("birthday"));
        employee.setPosition(resultSet.getString("position"));
        employee.setSalary(resultSet.getFloat("salary"));
        return employee;
    }

    @Override
    @Transactional
    public void removeEmployee(int employee_id) throws SQLException {
        removeFromTable(employee_id, "employee");
    }

    public void setDataSource(ComboPooledDataSource dataSource) {
        this.dataSource = dataSource;
    }

}
