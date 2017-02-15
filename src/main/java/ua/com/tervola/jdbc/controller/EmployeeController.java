package ua.com.tervola.jdbc.controller;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import ua.com.tervola.jdbc.model.Employee;
import ua.com.tervola.jdbc.model.EmployeeDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11/7/2016.
 */
@Component
public class EmployeeController {

//    private PlatformTransactionManager txManager;
    private EmployeeDao employeeDao;

    public EmployeeController(EmployeeDao employeeDao) {
//        this.txManager = txManager;
        this.employeeDao = employeeDao;
    }

//    @Transactional(propagation = Propagation.REQUIRED)
//    @Transactional
    public List<Employee> getAllEmployees(){
        return employeeDao.findAll();
    }

//    @Transactional(propagation = Propagation.REQUIRED)
//    @Transactional
    public Employee findById(int id){
        return employeeDao.findById(id);
    }

    public Employee findByName(String name){
        return employeeDao.findByName(name);
    }

    public void addEmployee(Employee employee){
        employeeDao.addEmployee(employee);
    }

    public void removeEmployee(int employee_id) throws SQLException {
        employeeDao.removeEmployee(employee_id);
    }

    public List<String> getAllEmployeesAsString(){
        List<String> rval = new ArrayList<>();
        for (Employee employee : employeeDao.findAll()) {
            rval.add(employee.toString());
        }
        return rval;
    }

}
