package ua.com.tervola.jdbc.consolepart;

import ua.com.tervola.jdbc.ProjectOperations;
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

    private static String OUTLINE_INSERT = "INSERT INTO %s VALUES (";
    private static String OUTLINE_DELETE = "DELETE FROM %s WHERE %s = %s";

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

    public String parseCommand(ProjectOperations projectOperation, String input, List<String> tableSize, ProjectTables projectTables) {
        String rval = "";

        if (input.endsWith(",")) {
            input = input.substring(0, input.length() - 1);
        }

        if (projectOperation.equals(ProjectOperations.INSERT)) {
            String[] split = input.split(",");
            if (split.length != tableSize.size()) {
                return "ERROR: Miss one or move values";
            }

            StringBuilder outLine = new StringBuilder();
            outLine.append(String.format(OUTLINE_INSERT, projectTables.toString()));
            String prefix = "";
            for (String line : split) {
                boolean isDigit = false;
                if (line.contains(",")) {
                    try {
                        Double.parseDouble(line);
                        isDigit = true;
                    } catch (Exception e) {
                        //NoOP
                    }
                } else if (Character.isDigit(line.toCharArray()[0])) {
                    try {
                        Integer.parseInt(line);
                        isDigit = true;
                    } catch (Exception e) {
                        //NoOP
                    }
                }

                if (!isDigit) {
                    line = String.format("'%s'", line);
                }

                outLine.append(prefix);
                prefix = ",";
                outLine.append(line);
            }
            outLine.append(")");
            rval = outLine.toString();
        } else if (projectOperation.equals(ProjectOperations.Delete)){
            String[] split = input.split(",");
            if (split.length != 2) {
                return "ERROR: Miss one or move values";
            }

            StringBuilder outLine = new StringBuilder();
            String field = split[0];
            String someField = split[1];
            outLine.append(String.format(OUTLINE_DELETE, projectTables.toString(),field, someField));
        }
        return rval;
    }
}
