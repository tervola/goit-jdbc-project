package ua.com.tervola.jdbc.model;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by user on 11/7/2016.
 */
public interface EmployeeDao {

    Employee findById(int id);

    List<Employee> findAll();

    Employee findByName(String employeeName);

    void addEmployee(Employee employee);

    void removeEmployee(int employee_id) throws SQLException;
}
