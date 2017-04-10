package ua.com.tervola.jdbc.consolepart;

import ua.com.tervola.jdbc.ProjectOperations;
import ua.com.tervola.jdbc.ProjectTables;
import ua.com.tervola.jdbc.controller.DatabaseController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by user on 3/1/2017.
 */
public class CrudOperation {


    DatabaseController databaseController;

    ConsoleValidator consoleValidator;
    ConsolePrinter consolePrinter = new ConsolePrinter();
    private List<ProjectTables> tables;

    public CrudOperation(ConsoleValidator consoleValidator, List<ProjectTables> tablesMenu, DatabaseController databaseController) {
        this.consoleValidator = consoleValidator;
        this.tables = tablesMenu;
        this.databaseController = databaseController;
    }

    public void runOperation(ProjectOperations projectOperation) throws IOException {
        String operation = "";
        while (true) {
            consolePrinter.print(String.format("Chose table for operation %s:", projectOperation.toString().toUpperCase()));
            int index = 1;
            for (ProjectTables table : tables) {
                System.out.println(index + ". " + table.toString());
                index++;
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = br.readLine().toLowerCase();
            int inputAfterValidation = consoleValidator.getInputNumber(input);
            if (inputAfterValidation == -2) {
                break;
            } else {
                ProjectTables projectTables = tables.get(inputAfterValidation - 1);
                operation = getOperation(projectOperation, projectTables);
                break;
            }
        }
        doOperation(operation);
    }

    private void doOperation(String operation) {
        this.databaseController.updateTable(operation);
    }

    private String getOperation(ProjectOperations projectOperation, ProjectTables projectTables) throws IOException {
        String operationLine = "";

        while (true) {
            consolePrinter.print(String.format("\n\n---\nType command  for '%s' on table: %s", projectOperation.toString().toUpperCase(), projectTables.toString().toUpperCase()));
            StringBuilder sampleString = new StringBuilder();
            List<String> fieldsInTable = null;
            if (projectOperation.equals(ProjectOperations.INSERT)) {
                fieldsInTable = this.databaseController.getFieldsInTable(projectTables.toString());
                sampleString.append(String.format("INSERT INTO %s VALUES (",
                        projectTables.toString()));

                String prefix = "";
                for (String column : fieldsInTable) {
                    sampleString.append(prefix);
                    prefix = ",";
                    sampleString.append(column);
                }
                sampleString.append(")");
                sampleString.append("\nTape only values separated by commas:");
            } else if (projectOperation.equals(ProjectOperations.Delete)) {
                sampleString.append(String.format("(DELETE FROM %s WHERE field = some_field", projectTables.toString()));
                sampleString.append("\nFields:");

                String prefix = "";
                for (String column : fieldsInTable) {
                    sampleString.append(prefix);
                    prefix = ",";
                    sampleString.append(column);
                }
                sampleString.append("\nTape 'field' and 'some_field' separated by commas:");

            } else if (projectOperation.equals(ProjectOperations.UPDATE)) {
                sampleString.append(String.format("(Example: %s %s SET field = some_field WHERE field_id = some_field_id", projectOperation.toString(), projectTables.toString()));
            }
            consolePrinter.print(sampleString.toString());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = br.readLine().toLowerCase();
            int inputAfterValidation = consoleValidator.getInputNumber(input);
            if (inputAfterValidation == -2) {
                break;
            }

            operationLine = consoleValidator.parseCommand(projectOperation, input, fieldsInTable, projectTables);
            if (!operationLine.startsWith("ERROR:")) {
                System.out.println("Command will perform:\n " + operationLine);
                break;
            }
        }
        return operationLine;
    }

}
