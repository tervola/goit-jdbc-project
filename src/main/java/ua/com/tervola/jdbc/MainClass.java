package ua.com.tervola.jdbc;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.com.tervola.jdbc.controller.EmployeeController;
import ua.com.tervola.jdbc.model.DatabaseDao;
import ua.com.tervola.jdbc.model.Employee;
import ua.com.tervola.jdbc.model.EmployeeDao;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by user on 11/3/2016.
 */
public class MainClass {
    private EmployeeController employeeController;
    private Logger LOGGER = LogManager.getLogger(MainClass.class);

    EmployeeDao employeeDao;
    DatabaseDao databaseDao;

    public void setEmployee(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        MainClass main = context.getBean(MainClass.class);
//        main.start();
//        main.startController();
//            main.startFromDB();
        main.addNewEmploee();
    }

    private void addNewEmploee() throws SQLException {

        Employee employee = new Employee();
        int id = getIndex();
        employee.setId(id);
        employee.setBirthday("1980-11-14");
        employee.setName("Anna" + id);
        employee.setPosition("waitress");
        employee.setPhone("067-123-19-45");
        employee.setSalary(460);
        employee.setSurName("Morozova" + id);
        employeeDao.addEmployee(employee);
    }

    private int getIndex() throws SQLException {
        List<Integer> indexes = employeeDao.getIndexes();
        int index = getRandomIndex();
        Collections.sort(indexes);
        if (indexes.contains(index)) {
            index = indexes.get(indexes.size() - 1) + 1;
        }
        return index;
    }

    private int getRandomIndex() {
        return new Random().nextInt(100);
    }

    private void startFromDB() throws SQLException {
        DatabaseDao sd = databaseDao;
        System.out.println("All tables list: " + databaseDao.getAllTables());
    }


    private void start() throws ClassNotFoundException, SQLException {
        LOGGER.info("prepared to call to DAO from start");
        employeeDao.findAll().forEach(System.out::println);
        System.out.println("---------");
        System.out.println(employeeDao.findById(1));


    }

    private void startController() throws ClassNotFoundException, SQLException {
        LOGGER.info("prepared to call to DAO from startController");
        employeeController.getAllEmployees().forEach(System.out::println);
        System.out.println("---------");
        System.out.println(employeeController.find(1));

    }

    public void setEmployeeController(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }

    public void setDatabaseDao(DatabaseDao databaseDao) {
        this.databaseDao = databaseDao;
    }


}
