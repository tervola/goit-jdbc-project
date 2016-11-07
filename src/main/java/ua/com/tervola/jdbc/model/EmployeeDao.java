package ua.com.tervola.jdbc.model;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/7/2016.
 */
public class EmployeeDao  {

    private static Logger LOGGER = LogManager.getLogger(EmployeeDao.class);

    String url = "jdbc:postgresql://localhost:5436/goit_db";
    String password = "postgres";
    String user = "postgres";

    public EmployeeDao() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
    }

    public List<Employee> findAll(){

        List<Employee> result = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from employee");
            while(resultSet.next()){
                Employee employee = createEmployee(resultSet);
                result.add(employee);
            }
        } catch (SQLException e) {
            LOGGER.error("Error occured during connection to" + url);
            throw new RuntimeException(e);
        }
        return result;
    }

    public Employee findById(int id){
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee WHERE id_empl = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return createEmployee(resultSet);
            } else {
                throw new RuntimeException("No records regarding ID");
            }

        } catch (SQLException e){
            LOGGER.error("Error occured during connection to" + url);
            throw new RuntimeException(e);
        }
    }

    private Employee createEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("id_empl"));
        employee.setSurName(resultSet.getString("surName"));
        employee.setName(resultSet.getString("name"));
        employee.setPhone(resultSet.getString("phone"));
        employee.setBirthday(resultSet.getString("birthday"));
        employee.setPosition(resultSet.getString("position"));
        employee.setSalary(resultSet.getFloat("salary"));
        return employee;
    }
}
