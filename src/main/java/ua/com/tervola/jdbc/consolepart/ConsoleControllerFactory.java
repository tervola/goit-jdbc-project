package ua.com.tervola.jdbc.consolepart;

import ua.com.tervola.jdbc.MainMenu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 * Created by user on 2/15/2017.
 */
public class ConsoleControllerFactory {

    private static ConsolePrinter consolePrinter;
    private static ConsoleValidator consoleValidator;

    //TODO: move menu list
    private static List<MainMenu> MAIN_MENU = Arrays.asList(MainMenu.TABLES, MainMenu.SHOWTABLES, MainMenu.EXIT);
    private static List<Integer> INTERURPT_LIST = Arrays.asList(-3, -2, -1, 0);

//    private List<String> getRecordList(){
//
//    }
//
//    public static List<String> selectRecordsFromTable() {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//
//        while (true) {
//            String input = br.readLine().toLowerCase();
//            int result = consoleValidator.validateInput(input);
//
//            if (INTERURPT_LIST.contains(result) || result > MAIN_MENU.size()) {
//                if (interruptHandling(result)) {
//                    break;
//                }
//            } else {
//                MainMenu mainMenu = MAIN_MENU.get(result - 1);
//                consolePrinter.print(consoleMenuFactory.createController(mainMenu));
//                consolePrinter.printMainMenu(MAIN_MENU);
//            }
//        }
//    }
//
//    private boolean interruptHandling(int result) {
//        boolean rval = false;
//        if (result == -2 || result == MAIN_MENU.size()) {
//            consolePrinter.printGoodBuy();
//            rval = true;
//        } else if (result == -3 || result == 0 || result > MAIN_MENU.size()) {
//            consolePrinter.printRepeat();
//        } else if (result == -1) {
//            consolePrinter.print(new String());
//        }
//        return rval;
//    }
}
