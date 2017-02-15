package ua.com.tervola.jdbc.consolepart;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

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
    private static List<String> MAIN_MENU = Arrays.asList("List of Tables", "Exit");


    
    ConsolePrinter consolePrinter;
    ConsoleValidator consoleValidator;
    ConsoleControllerFactory consoleControllerFactory;

    public void run() throws IOException, SQLException {
        consolePrinter.printHead(MAIN_MENU);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String input = br.readLine().toLowerCase();
            int result = consoleValidator.validateInput(input);

            if (result == 0 || result == MAIN_MENU.size()) {
                consolePrinter.printGoodBuy();
                break;
            } else if (result == -1 || result > MAIN_MENU.size()){
                consolePrinter.printRepeat();
            } else if (result == -2) {
                consolePrinter.print(new String());
            }

            consolePrinter.print(consoleControllerFactory.createController(result));
            consolePrinter.printMainmenu(MAIN_MENU);
        }
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
