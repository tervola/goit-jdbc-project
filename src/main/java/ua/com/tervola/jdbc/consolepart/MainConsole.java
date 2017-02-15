package ua.com.tervola.jdbc.consolepart;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.com.tervola.jdbc.MainMenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

/**
 * Created by user on 2/15/2017.
 */
public class MainConsole {
    private Logger LOGGER = LogManager.getLogger(MainConsole.class);
    private static List<MainMenu> MAIN_MENU = Arrays.asList(MainMenu.TABLES, MainMenu.EXIT);
    private static List<Integer> INTERURPT_LIST = Arrays.asList(-3,-2,-1,0);



    ConsolePrinter consolePrinter;
    ConsoleValidator consoleValidator;
    ConsoleControllerFactory consoleControllerFactory;

    public void run() throws IOException, SQLException {
        consolePrinter.printHead(MAIN_MENU);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String input = br.readLine().toLowerCase();
            int result = consoleValidator.validateInput(input);

            if(INTERURPT_LIST.contains(result) || result > MAIN_MENU.size()){
                if (interruptHandling(result)) {
                    break;
                }
            } else {
                MainMenu mainMenu = MAIN_MENU.get(result - 1);
                consolePrinter.print(consoleControllerFactory.createController(mainMenu));
                consolePrinter.printMainmenu(MAIN_MENU);
            }
        }
    }

    private boolean interruptHandling(int result) {
        boolean rval = false;
        if (result == -2 || result == MAIN_MENU.size()) {
            consolePrinter.printGoodBuy();
            rval = true;
        } else if (result == -3 || result == 0 || result > MAIN_MENU.size()) {
            consolePrinter.printRepeat();
        } else if (result == -1) {
            consolePrinter.print(new String());
        }
        return rval;
    }


    public void setConsolePrinter(ConsolePrinter consolePrinter) {
        this.consolePrinter = consolePrinter;
    }

    public void setConsoleValidator(ConsoleValidator consoleValidator) {
        this.consoleValidator = consoleValidator;
    }

    public void setConsoleControllerFactory(ConsoleControllerFactory consoleControllerFactory) {
        this.consoleControllerFactory = consoleControllerFactory;
    }
}
