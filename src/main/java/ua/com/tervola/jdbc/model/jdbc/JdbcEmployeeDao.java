package ua.com.tervola.jdbc.model.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.tervola.jdbc.controller.DatabaseController;
import ua.com.tervola.jdbc.model.Employee;
import ua.com.tervola.jdbc.model.EmployeeDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/4/2016.
 */
public class JdbcEmployeeDao extends AbstractJdbcTablesDao implements EmployeeDao {

    private static Logger LOGGER = LogManager.getLogger(JdbcEmployeeDao.class);
    private static String TABLE_EMPLOYEE = "employee";
    private static String FIELD_EMPLOYEE_ID = "employee_id";
    private static String FIELD_SURNAME = "surname";
    private static String FIELD_NAME = "name";
    private static String FIELD_BIRTHDAY = "birthday";
    private static String FIELD_PHONE = "phone";
    private static String FIELD_POSITION = "position";
    private static String FIELD_SALARY = "salary";

    public JdbcEmployeeDao(DatabaseController databaseController) {
        super(databaseController);
    }


    @Override
    public List<Employee> findAll() {
        List<Employee> result = new ArrayList<>();
        try {
            ResultSet resultSet = findIntabledAllRecords(TABLE_EMPLOYEE);
            while (resultSet.next()) {
                Employee employee = createEmployee(resultSet);
                result.add(employee);
            }
        } catch (SQLException e) {
//            LOGGER.error("Error occured during connection to " + dataSource.getJdbcUrl());
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Employee findById(int id) {
        try {
            ResultSet resultSet = findInTable(String.valueOf(id), TABLE_EMPLOYEE, FIELD_EMPLOYEE_ID);
            if (resultSet.next()) {
                return createEmployee(resultSet);
            } else {
                throw new RuntimeException("Cannot findById employee with id " + id);
            }

        } catch (SQLException e) {
//            LOGGER.error("Error occured during connection to " + dataSource.getJdbcUrl() + " User is:" + dataSource.getUser() + " Password is: " + dataSource.getPassword());
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee findByName(String employeeName) {
        try {
            ResultSet resultSet = findInTable(employeeName, TABLE_EMPLOYEE, FIELD_NAME);
            if (resultSet.next()) {
                return createEmployee(resultSet);
            } else {
                throw new RuntimeException("Cannot findById employee with name: " + employeeName);
            }

        } catch (SQLException e) {
//            LOGGER.error("Error occured during connection to " + dataSource.getJdbcUrl() + " User is:" + dataSource.getUser() + " Password is: " + dataSource.getPassword());
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public void addEmployee(Employee employee) {
        try {
            Connection connection = getDatabaseController().getConnection();
            StringBuilder sqlCommand = new StringBuilder();
            sqlCommand.append(String.format("INSERT INTO %s (%s,%s,%s,%s,%s,%s )",
                    TABLE_EMPLOYEE,
                    FIELD_EMPLOYEE_ID,
                    FIELD_SURNAME,
                    FIELD_NAME,
                    FIELD_BIRTHDAY,
                    FIELD_PHONE,
                    FIELD_POSITION,
                    FIELD_SALARY));
            sqlCommand.append("VALUES (?,?,?,?,?,?,?)");

            PreparedStatement preparedStatement = connection.prepareStatement(sqlCommand.toString());
            preparedStatement.setInt(1, employee.getId());
            preparedStatement.setString(2, employee.getSurName());
            preparedStatement.setString(3, employee.getName());
            preparedStatement.setDate(4, Date.valueOf(employee.getBirthday()));
            preparedStatement.setString(5, employee.getPhone());
            preparedStatement.setString(6, employee.getPosition());
            preparedStatement.setFloat(7, employee.getSalary());

            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            LOGGER.error("Error, while updating EMPLOYEE table");
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private List<Integer> getIndexes() throws SQLException {
        return getIndexesFromTable(FIELD_EMPLOYEE_ID, TABLE_EMPLOYEE);
    }

    private Employee createEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt(FIELD_EMPLOYEE_ID));
        employee.setSurName(resultSet.getString(FIELD_SURNAME));
        employee.setName(resultSet.getString(FIELD_NAME));
        employee.setPhone(resultSet.getString(FIELD_PHONE));
        employee.setBirthday(resultSet.getString(FIELD_BIRTHDAY));
        employee.setPosition(resultSet.getString(FIELD_POSITION));
        employee.setSalary(resultSet.getFloat(FIELD_SALARY));
        return employee;
    }

    @Override
//    @Transactional
    public void removeEmployee(int employee_id) throws SQLException {
        removeFromTable(employee_id, TABLE_EMPLOYEE, FIELD_EMPLOYEE_ID);
    }

}
