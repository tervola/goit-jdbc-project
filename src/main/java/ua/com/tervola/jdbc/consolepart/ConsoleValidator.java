package ua.com.tervola.jdbc.consolepart;

import ua.com.tervola.jdbc.ProjectTables;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by user on 2/15/2017.
 */
public class ConsoleValidator {
    private static String COMMAND_EXIT = "exit";
    private static String COMMAND_E = "e";
    private static String COMMAND_QUIT = "quit";
    private static String COMMAND_Q = "q";

    private static String TEXT_EMPTY = "";

    private static int INPUT_ENTER = -1;
    private static int INPUT_EXIT = -2;
    private static int INPUT_WRONG_FORMAT = -3;

    public int getInputNumber(String inputText) throws IOException {

        Integer x = CheckEmptyorExit(inputText);
        if (x != null) return x;

        try {
            int chose = Integer.parseInt(inputText);
            if (chose > 0) {
                return chose;
            }
            return INPUT_WRONG_FORMAT;
        } catch (NumberFormatException e) {
            return INPUT_WRONG_FORMAT;
        }
    }

    private Integer CheckEmptyorExit(String inputText) {
        if (inputText.trim().equals(TEXT_EMPTY)) {
            return INPUT_ENTER;
        }

        if (inputText.equals(COMMAND_EXIT) || inputText.equals(COMMAND_QUIT) || inputText.equals(COMMAND_E) || inputText.equals(COMMAND_Q)) {
            return INPUT_EXIT;
        }
        return null;
    }

}
