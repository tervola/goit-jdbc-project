package ua.com.tervola.jdbc.consolepart;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by user on 2/15/2017.
 */
public class ConsolePrinter {

    private Logger LOGGER = LogManager.getLogger(ConsolePrinter.class);

    private static String TEXT_HEAD = "MAIN PROJECT CONSOLE. Start from 2/15/2017";
    private static String TEXT_GOODBUY = "Goodbye!";
    private static String TEXT_WRONG_INPUT = "Wrong input, try again!";
    private static String TEXT_START_RESULT = "<=== FINISH ===>";
    private static String TEXT_END_RESULT = "<=== FINISH ===>";
    private static String TEXT_TOTAL_RECORDS = "Total records: ";

    private static String MAIN_INPUT = "\nChose action you want (type exit or quit for finish):\n";


    public void print(String s){
        System.out.println(s);
    }

    public void print(int number){
        print(String.valueOf(number));
    }

    public void printHead(List<String> mainMenu) {
        print(TEXT_HEAD);
        print(MAIN_INPUT);
        int index = 1;
        for (String s : mainMenu) {
            String line = String.format("%s. %s",index,s );
            print(line);
            index++;
        }
    }

    public void printGoodBuy() {
        print(TEXT_GOODBUY);
    }

    public void printRepeat() {
        print(TEXT_WRONG_INPUT);
    }

    public void print(List<String> list){
        print(TEXT_START_RESULT);
        for (String s : list) {
            print(s);
        }
        print(TEXT_TOTAL_RECORDS + list.size());
        print(TEXT_END_RESULT);
    }

    public void printMainmenu(List<String> mainMenu) {
        print(MAIN_INPUT);
        int index = 1;
        for (String s : mainMenu) {
            String line = String.format("%s. %s",index,s );
            print(line);
            index++;
        }
    }
}
