package ua.com.tervola.jdbc.controller;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import ua.com.tervola.jdbc.model.Employee;
import ua.com.tervola.jdbc.model.EmployeeDao;

import java.util.List;

/**
 * Created by user on 11/7/2016.
 */
public class EmployeeController {

    private PlatformTransactionManager txManager;
    private EmployeeDao employeeDao;

    public EmployeeController(PlatformTransactionManager txManager, EmployeeDao employeeDao) {
        this.txManager = txManager;
        this.employeeDao = employeeDao;
    }

//    @Transactional(propagation = Propagation.REQUIRED)
//    @Transactional
    public List<Employee> getAllEmployees(){
        return employeeDao.findAll();
    }

//    @Transactional(propagation = Propagation.REQUIRED)
//    @Transactional
    public Employee find(int id){
        return employeeDao.findById(id);
    }


}
