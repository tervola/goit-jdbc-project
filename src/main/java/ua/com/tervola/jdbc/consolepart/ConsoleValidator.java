package ua.com.tervola.jdbc.consolepart;

import ua.com.tervola.jdbc.ProjectOperations;
import ua.com.tervola.jdbc.ProjectTables;

import java.io.IOException;
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
    private static String OUTLINE_DELETE = "DELETE FROM %s WHERE %s";
    private static String OUTLINE_UPDATE = "UPDATE %s SET %s";

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
        StringBuilder outLine = new StringBuilder();

        if (projectOperation.equals(ProjectOperations.INSERT)) {
            String[] split = input.split(",");
            if (split.length != tableSize.size()) {
                return "ERROR: Miss one or move values";
            }

            outLine.append(String.format(OUTLINE_INSERT, projectTables.toString()));
            String prefix = "";
            for (String line : split) {
                line = addQuotesIfNeed(line);
                outLine.append(prefix);
                prefix = ",";
                outLine.append(line);
            }
            outLine.append(")");

        } else if (projectOperation.equals(ProjectOperations.UPDATE)) {

            /**
             * Inputstring for parsing should be:
             * dish_di=13,coast=215
            */
            String[] split = input.split(",");
            if (split.length != 2) {
                return "ERROR: Miss one or move values";
            }

            String firstCondition = split[0];
            String field = firstCondition.split("=")[0];
            String someField = firstCondition.split("=")[1];
            String secondCondition = split[1];
            String fieldCondition= secondCondition.split("=")[0];
            String someFieldCondition = secondCondition.split("=")[1];

            StringBuilder condition = new StringBuilder();
            condition.append(field).append("=");
            condition.append(addQuotesIfNeed(someField)).append(" WHERE ");
            condition.append(fieldCondition).append("=");
            condition.append(addQuotesIfNeed(someFieldCondition));
            outLine.append(String.format(OUTLINE_UPDATE, projectTables.toString(), condition.toString()));
        } else if (projectOperation.equals(ProjectOperations.DELETE)) {
            String[] split = input.split("\\W");
            if (split.length != 2) {
                return "ERROR: Miss one or move values";
            }

            String field = split[0];
            String someField = split[1];

            StringBuilder condition = new StringBuilder();
            condition.append(field).append(" ");
            condition.append(getOp(input)).append(" ");
            someField = addQuotesIfNeed(someField);
            condition.append(someField);
            outLine.append(String.format(OUTLINE_DELETE, projectTables.toString(), condition.toString()));
        }
        return outLine.toString();
    }

    private String getOp(String input) {
        input = input.replace(" ", "");
        int index = -1000;
        if (input.contains("<")) {
            index = input.indexOf("<");
        } else if (input.contains("=")) {
            index = input.indexOf("=");
        } else if (input.contains(">")) {
            index = input.indexOf("=");
        }

        if (index == -1000) {
            throw new RuntimeException("unknown operand");
        }

        if (Character.isLetterOrDigit(input.charAt(index + 1))) {
            return String.valueOf(input.charAt(index));
        } else {
            return String.valueOf(input.charAt(index) + input.charAt(index + 1));
        }
    }

    private String addQuotesIfNeed(String someField) {
        String rval = someField;
        boolean isDigit = false;
        if (rval.contains(",")) {
            try {
                Double.parseDouble(rval);
                isDigit = true;
            } catch (Exception e) {
                //NoOP
            }
        } else if (Character.isDigit(rval.toCharArray()[0])) {
            try {
                Integer.parseInt(rval);
                isDigit = true;
            } catch (Exception e) {
                //NoOP
            }
        }

        if (!isDigit) {
            rval = String.format("'%s'", rval);
        }
        return rval;
    }

//    private String defineCondition(String input) {
//    }
}
