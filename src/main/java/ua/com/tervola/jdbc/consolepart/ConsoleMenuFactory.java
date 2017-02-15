package ua.com.tervola.jdbc.consolepart;

import ua.com.tervola.jdbc.MainMenu;
import ua.com.tervola.jdbc.ProjectTables;
import ua.com.tervola.jdbc.controller.*;
import ua.com.tervola.jdbc.model.DatabaseDao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by user on 2/15/2017.
 */
public class ConsoleMenuFactory {
    private DatabaseDao databaseDao;
    private DatabaseController databaseController;
    private EmployeeController employeeController;
    private DishController dishController;
    private MenuController menuController;
    private OrderController orderController;
    private PreparedDishesController preparedDishesController;
    private StoreController storeController;

    private ConsolePrinter consolePrinter = new ConsolePrinter();
    private static List<ProjectTables> SHOW_TABLES_MENU = Arrays.asList(
            ProjectTables.DISH,
            ProjectTables.EMPLOYEE,
            ProjectTables.MENU,
            ProjectTables.ORDER,
            ProjectTables.PREPARED_DISHES,
            ProjectTables.STORAGE);

    public void createController(MainMenu mainMenu) throws SQLException, IOException {
        List<String> result = new ArrayList<>();


        switch (mainMenu) {
            case TABLES:
                consolePrinter.print(databaseController.getAllTables());
                break;
            case SHOWTABLES:
                //TODO: move to class
                //TODO: add validator
                consolePrinter.print("Entering to \"Show records from table\"..");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                consolePrinter.printShowTablesMenu(SHOW_TABLES_MENU);
                while (true) {
                    String input = br.readLine().toLowerCase();
                    if (input.equals("dish")) {
                        consolePrinter.print(dishController.findAllDishesAsStringList());
                    } else if (input.equals("employee")) {
                        consolePrinter.print(employeeController.getAllEmployeesAsString());
                    } else if (input.equals("menu")) {
                        consolePrinter.print(menuController.findAllMenuAsString());
                    } else if (input.equals("order")) {
                        consolePrinter.print(orderController.findClosedOrdersAsString());
                    } else if (input.equals("prepared_dishes")) {
                        consolePrinter.print(preparedDishesController.findAllPreparedDishesAsString());
                    } else if (input.equals("storage")) {
                        consolePrinter.print(storeController.findAllIngridientsAsString());
                    } else if (input.equals("exit")) {
                        break;
                    } else {
                        consolePrinter.printRepeathowRecordsMenu();
                    }
                }
                break;

        }

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
