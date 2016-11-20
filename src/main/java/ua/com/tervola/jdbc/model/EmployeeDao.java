package ua.com.tervola.jdbc.model;

import java.util.List;

/**
 * Created by user on 11/7/2016.
 */
public interface EmployeeDao {

    Employee findById(int id);

    List<Employee> findAll();
}
