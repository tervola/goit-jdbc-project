package ua.com.tervola.jdbc.consolepart;

import ua.com.tervola.jdbc.MainMenu;
import ua.com.tervola.jdbc.ProjectTables;
import ua.com.tervola.jdbc.controller.*;
import ua.com.tervola.jdbc.model.DatabaseDao;
import ua.com.tervola.jdbc.ProjectOperations;

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
    ConsoleValidator consoleValidator = new ConsoleValidator();
    private static List<ProjectTables> TABLES_MENU = Arrays.asList(
            ProjectTables.DISH,
            ProjectTables.EMPLOYEE,
            ProjectTables.MENU,
            ProjectTables.ORDER,
            ProjectTables.PREPARED_DISHES,
            ProjectTables.STORAGE);
    private static List<ProjectOperations> CRUD_OPERATIONS = Arrays.asList(
            ProjectOperations.INSERT,
            ProjectOperations.UPDATE,
            ProjectOperations.Delete);

    public void createController(MainMenu mainMenu) throws SQLException, IOException {
        List<String> result = new ArrayList<>();
        switch (mainMenu) {
            case TABLES:
                this.consolePrinter.print(databaseController.getAllTables());
                break;
            case SHOWTABLES:
                //TODO: move to class

                while (true) {
                    consolePrinter.print("Entering to \"Show records from table\".. 'e' or 'q' to exit previous menu");
                    consolePrinter.printShowTablesMenu(TABLES_MENU);

                    int inputAfterValidation = inputAndValidate();

                    if (inputAfterValidation == 1) {
                        consolePrinter.print(dishController.findAllDishesAsStringList());
                    } else if (inputAfterValidation == 2) {
                        consolePrinter.print(employeeController.getAllEmployeesAsString());
                    } else if (inputAfterValidation == 3) {
                        consolePrinter.print(menuController.findAllMenuAsString());
                    } else if (inputAfterValidation == 4) {
                        consolePrinter.print(orderController.findClosedOrdersAsString());
                    } else if (inputAfterValidation == 5) {
                        consolePrinter.print(preparedDishesController.findAllPreparedDishesAsString());
                    } else if (inputAfterValidation == 6) {
                        consolePrinter.print(storeController.findAllIngridientsAsString());
                    } else if (inputAfterValidation == -2) {
                        break;
                    } else {
                        consolePrinter.printRepeatShowRecordsMenu();
                    }
                }
                break;
            case OPERATIONS:
                while (true) {
                    System.out.println("Enter CRUD command 'e' or 'q' to exit previous menu:");
                    int index = 1;
                    for (ProjectOperations crudOperation : CRUD_OPERATIONS) {
                        System.out.println(index++ + ". " + crudOperation);
                    }


                    int inputAfterValidation = inputAndValidate();

                    if (inputAfterValidation == -2) {
                        break;
                    }
                    ProjectOperations projectOperations = CRUD_OPERATIONS.get(inputAfterValidation - 1);
                    CrudOperation crudOperation = new CrudOperation(this.consoleValidator, TABLES_MENU, databaseController);

                    crudOperation.runOperation(projectOperations);
                    break;
                }
                break;
        }
    }

    private int inputAndValidate() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine().toLowerCase();
        return this.consoleValidator.getInputNumber(input);
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
