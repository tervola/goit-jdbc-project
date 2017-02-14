package ua.com.tervola.jdbc;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.com.tervola.jdbc.controller.*;
import ua.com.tervola.jdbc.model.DatabaseDao;
import ua.com.tervola.jdbc.model.Employee;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by user on 11/3/2016.
 */
public class MainClass {
    private Logger LOGGER = LogManager.getLogger(MainClass.class);

    DatabaseDao databaseDao;
    DatabaseController databaseController;
    EmployeeController employeeController;
    DishController dishController;
    MenuController menuController;
    OrderController orderController;
    PreparedDishesController preparedDishesController;
    StoreController storeController;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        MainClass main = context.getBean(MainClass.class);
        main.start();
//        main.startController();
//            main.startFromDB();
//        main.addNewEmploee();
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
//        employeeDao.addEmployee(employee);
    }

    private int getIndex() throws SQLException {
//        List<Integer> indexes = employeeDao.getIndexes();
//        int index = getRandomIndex();
//        Collections.sort(indexes);
//        if (indexes.contains(index)) {
//            index = indexes.get(indexes.size() - 1) + 1;
//        }
//        return index;
        return -1;
    }

    private int getRandomIndex() {
        return new Random().nextInt(100);
    }

//    private void startFromDB() throws SQLException {
//        DatabaseDao sd = databaseDao;
//        System.out.println("All tables list: " + databaseDao.getAllTables());
//    }


    private void start() throws ClassNotFoundException, SQLException {
        LOGGER.info("prepared to call to Database from start");
        System.out.println("Database:");
        databaseController.getAllTables().forEach(System.out::println);
        System.out.println("---------");
        System.out.println(employeeController.findById(1));

        LOGGER.info("prepared to call to DAO from start");
        System.out.println("Employee:");
        employeeController.getAllEmployees().forEach(System.out::println);
        System.out.println("---------");
        System.out.println(employeeController.findById(1));

        LOGGER.info("prepared to call to DishDAO");
        System.out.println("Dish:");
        dishController.findAllDishes().forEach(System.out::println);
        System.out.println("---------");
        System.out.println(dishController.findDishById(1));

        LOGGER.info("prepared to call to MenuDAO ");
        System.out.println("Menu");
        menuController.findAllMenu().forEach(System.out::println);
        System.out.println("---------");
        System.out.println(menuController.findMenuById(1));

        //TODO: orders closed/opened
        LOGGER.info("prepared to call to OrderDAO ");
        System.out.println("Opened Orders");
        orderController.findOpenedOrders().forEach(System.out::println);
        System.out.println("Closeded Orders");
        orderController.findOpenedOrders().forEach(System.out::println);
        System.out.println("---------");
        System.out.println(menuController.findMenuById(1));

        LOGGER.info("prepared to call to PreparedDishesDAO ");
        System.out.println("Prepared Dishes");
        preparedDishesController.findAllPreparedDishes().forEach(System.out::println);
        System.out.println("---------");

        LOGGER.info("prepared to call to StoreDao ");
        System.out.println("Storage");
        storeController.findAllIngridients().forEach(System.out::println);
        System.out.println("---------");

        System.out.println("FINISH");

    }

    private void startController() throws ClassNotFoundException, SQLException {
        LOGGER.info("prepared to call to DAO from preparedDishesController");
        preparedDishesController.findAllPreparedDishes().forEach(System.out::println);
        System.out.println("---------");
        System.out.println(employeeController.findById(1));

    }

    public void setEmployeeController(EmployeeController employeeController) {
        this.employeeController = employeeController;
    }

    public void setDatabaseDao(DatabaseDao databaseDao) {
        this.databaseDao = databaseDao;
    }


    public void setDatabaseController(DatabaseController databaseController) {
        this.databaseController = databaseController;
    }

    public void setDishController(DishController dishController) {
        this.dishController = dishController;
    }

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }

    public void setOrderController(OrderController orderController) {
        this.orderController = orderController;
    }

    public void setPreparedDishesController(PreparedDishesController preparedDishesController) {
        this.preparedDishesController = preparedDishesController;
    }

    public void setStoreController(StoreController storeController) {
        this.storeController = storeController;
    }
}
