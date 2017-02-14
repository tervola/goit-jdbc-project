import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.*;
import org.junit.Test;
import ua.com.tervola.jdbc.controller.*;
import ua.com.tervola.jdbc.model.*;
import ua.com.tervola.jdbc.model.jdbc.*;

import java.awt.*;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

/**
 * Created by user on 2/10/2017.
 */
public class TestSelectFromTable extends Assert {
    private static List<String> ALL_TABLES = Arrays.asList(
            "storage",
            "menu",
            "concatenate_ing_dish",
            "prepared_dishes",
            "dish",
            "concatenate_oreder_dish",
            "employee",
            "ingridients",
            "concatenate_menu_dish",
            "order");

    private static String TABLE_EMPLOYEE = "employee";
    private static String TABLE_DISH = "dish";



    private DatabaseDao databaseDao;
    private DatabaseController databaseController;

    private EmployeeDao employeeDao;
    private EmployeeController employeeController;

    private DishDao dishDao;
    private DishController dishController;

    private MenuDao menuDao;
    private MenuController menuController;

    private OrderDao orderDao;
    private OrderController orderController;

    private PreparedDishesDao preparedDishesDao;
    private PreparedDishesController preparedDishesController;

    private StoreDao storeDao;
    private StoreController storeController;

    @Before
    public void initializeControllers() throws PropertyVetoException {
        this.databaseDao = new JdbcDatabaseDao();
        this.databaseDao.setDataSource(getDataSource());
        this.databaseController = new DatabaseController(this.databaseDao);

        this.employeeDao = new JdbcEmployeeDao(this.databaseController);
        this.employeeController = new EmployeeController(this.employeeDao);

        this.dishDao = new JdbcDishDao(this.databaseController);
        this.dishController = new DishController(this.dishDao);

        this.menuDao = new JdbcMenuDao(this.databaseController);
        this.menuController = new MenuController(this.menuDao);

        this.orderDao = new JdbcOrderDao(this.databaseController);
        this.orderController = new OrderController(this.orderDao);

        this.preparedDishesDao = new JdbcPreparedDishesDao(this.databaseController);
        this.preparedDishesController = new PreparedDishesController(this.preparedDishesDao);

        this.storeDao = new JdbcStoreDao(this.databaseController);
        this.storeController = new StoreController(this.storeDao);

    }

    @Test
    public void checkInstanses(){

        System.out.println("CHECK CONTROLLERS .. START");

        System.out.println("Check databaseController.. start");
        assertNotNull(this.databaseController);
        System.out.println("Check databaseController.. success");

        System.out.println("Check employeeController.. start");
        assertNotNull(this.employeeController);
        System.out.println("Check employeeController.. success");

        System.out.println("Check dishController.. start");
        assertNotNull(this.dishController);
        System.out.println("Check dishController.. success");

        System.out.println("Check menuController.. start");
        assertNotNull(this.menuController);
        System.out.println("Check menuController.. success");

        System.out.println("Check orderController.. start");
        assertNotNull(this.orderController);
        System.out.println("Check orderController.. success");

        System.out.println("Check preparedDishesController.. start");
        assertNotNull(this.preparedDishesController);
        System.out.println("Check preparedDishesController.. success");

        System.out.println("Check storeController.. start");
        assertNotNull(this.storeController);
        System.out.println("Check storeController.. success");

        System.out.println("CHECK CONTROLLERS .. SUCCESS");
    }

    @Test
    public void selectToTablesDao() throws SQLException {

        List<String> tableList = this.databaseController.getAllTables();
        Collections.sort(ALL_TABLES);
        Collections.sort(tableList);
        assertEquals(ALL_TABLES,tableList);


        List<Employee> allEmployees = this.employeeController.getAllEmployees();
        if (allEmployees.size() > 0) {
            Employee employee = allEmployees.get(0);
            assertNotNull(employee.getBirthday());
            assertNotNull(employee.getId());
            assertNotNull(employee.getName());
            assertNotNull(employee.getPhone());
            assertNotNull(employee.getPosition());
            assertNotNull(employee.getSalary());
            assertNotNull(employee.getSurName());
            System.out.println(ProjectTables.EMPLOYEE);
        } else {
            System.out.println(String.format("Table %s is empty",ProjectTables.EMPLOYEE));
        }

        List<Dish> allDishes = this.dishController.findAllDishes();
        if(allDishes.size() > 0){
            Dish dish = allDishes.get(0);
            assertNotNull(dish.getCategory());
            assertNotNull(dish.getCost());
            assertNotNull(dish.getDish_id());
            assertNotNull(dish.getTitle());
            assertNotNull(dish.getWeight());
        } else {
            System.out.println(String.format("Table %s is empty",ProjectTables.DISH));
        }
//        this.menuController.findAllMenu().forEach(System.out::println);
//        this.orderController.findOpenedOrders().forEach(System.out::println);
//        this.orderController.findClosedOrders().forEach(System.out::println);
//        this.preparedDishesController.findAllPreparedDishes().forEach(System.out::println);
//        this.storeController.findAllIngridients().forEach(System.out::println);

    }

    private ComboPooledDataSource getDataSource() throws PropertyVetoException {
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
//        comboPooledDataSource.setDriverClass("org.postgresql.Driver");
        comboPooledDataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/goit_db");
        comboPooledDataSource.setUser("postgres");
        comboPooledDataSource.setPassword("postgres");
        comboPooledDataSource.setMaxPoolSize(15);
        comboPooledDataSource.setMinPoolSize(1);
        comboPooledDataSource.setAcquireIncrement(1);
        return comboPooledDataSource;
    }


}
