package ua.com.tervola.jdbc.consolepart;

import ua.com.tervola.jdbc.controller.*;
import ua.com.tervola.jdbc.model.DatabaseDao;

import java.sql.SQLException;
import java.util.*;

/**
 * Created by user on 2/15/2017.
 */
public class ConsoleControllerFactory {
    private DatabaseDao databaseDao;
    private DatabaseController databaseController;
    private EmployeeController employeeController;
    private DishController dishController;
    private MenuController menuController;
    private OrderController orderController;
    private PreparedDishesController preparedDishesController;
    private StoreController storeController;

    public List<String> createController(int mainMenuNumber) throws SQLException {
        List<String> result = new ArrayList<>();
        switch (mainMenuNumber){
            case 1: result = this.databaseController.getAllTables();
        }
        return result;
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

    @Override
    public String toString() {
        return "MainConsole{" +
                "databaseDao=" + databaseDao +
                ",\n databaseController=" + databaseController +
                ",\n employeeController=" + employeeController +
                ",\n dishController=" + dishController +
                ",\n menuController=" + menuController +
                ",\n orderController=" + orderController +
                ",\n preparedDishesController=" + preparedDishesController +
                ",\n storeController=" + storeController +
                '}';
    }
}
