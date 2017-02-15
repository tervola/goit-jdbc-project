package ua.com.tervola.jdbc.consolepart;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.com.tervola.jdbc.MainMenu;
import ua.com.tervola.jdbc.ProjectTables;

import java.util.List;

/**
 * Created by user on 2/15/2017.
 */
public class ConsolePrinter {

    private Logger LOGGER = LogManager.getLogger(ConsolePrinter.class);

    private static String TEXT_HEAD = "MAIN PROJECT CONSOLE. Start from 2/15/2017";
    private static String TEXT_GOODBUY = "Goodbye!";
    private static String TEXT_WRONG_INPUT = "Wrong input, try again!";
    private static String TEXT_WRONG_TABLE_INPUT = "Unrecognized table, try again or type \"exit\" to return Main menu:";
    private static String TEXT_START_RESULT = "<=== FINISH ===>";
    private static String TEXT_END_RESULT = "<=== FINISH ===>";
    private static String TEXT_TOTAL_RECORDS = "Total records: ";

    private static String MAIN_INPUT = "\nChose action you want (type exit or quit for finish):\n";
    private static String SHOW_TABLE_INPUT = "\nInput number or type \"exit\" to return Main menu:\n";



    public void print(String s) {
        System.out.println(s);
    }

    public void print(int number) {
        print(String.valueOf(number));
    }

    public void printHead(List<MainMenu> mainMenu) {
        print(TEXT_HEAD);
        print(MAIN_INPUT);
        printMainMenu(mainMenu);
    }

    public void printGoodBuy() {
        print(TEXT_GOODBUY);
    }

    public void printRepeat() {
        print(TEXT_WRONG_INPUT);
    }

    public void print(List<String> list) {
        print(TEXT_START_RESULT);
        for (String s : list) {
            print(s);
        }
        print(TEXT_TOTAL_RECORDS + list.size());
        print(TEXT_END_RESULT);
    }

    public void printMainMenu(List<MainMenu> mainMenu) {
        print(MAIN_INPUT);
        int index = 1;
        for (MainMenu item : mainMenu) {
            String line = String.format("%s. %s", index, item);
            print(line);
            index++;
        }
    }

    public void printHeadOfShowRecordsMenu(){
        print(SHOW_TABLE_INPUT);
    }

    public void printRepeathowRecordsMenu(){
        print(TEXT_WRONG_TABLE_INPUT);
    }

    public void printShowTablesMenu(List<ProjectTables> projectTables) {
        int index = 1;
        for (ProjectTables item : projectTables) {
            String line = String.format("%s. %s", index, item);
            print(line);
            index++;
        }
        print(SHOW_TABLE_INPUT);
    }
}
