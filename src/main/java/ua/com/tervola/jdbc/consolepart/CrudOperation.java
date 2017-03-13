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

    public String getResult(ProjectOperations projectOperation) throws IOException {
        String operation = "";
        while (true) {
            consolePrinter.print(String.format("Chose base for operation %s:", projectOperation.toString()));
            int index = 1;
            for (ProjectTables table : tables) {
                System.out.println(index + ". " + table.toString());
                index++;
            }
            consolePrinter.printInLine("type:\\> ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = br.readLine().toLowerCase();
            int inputAfterValidation = consoleValidator.getInputNumber(input);
            if (inputAfterValidation == -2) {
                break;
            } else {
                ProjectTables projectTables = tables.get(inputAfterValidation - 1);
                operation = getOperation(projectOperation, projectTables);

            }
        }
        return operation;
    }

    private String getOperation(ProjectOperations projectOperation, ProjectTables projectTables) throws IOException {
        while (true) {
            consolePrinter.print(String.format("---\nType command  for '%s' on table: %s", projectOperation.toString(), projectTables.toString()));
            StringBuilder sampleString = new StringBuilder();
            if (projectOperation.equals(ProjectOperations.INSERT)) {
                sampleString.append(String.format("(Example: %s INTO %s VALUES (..)\n Fields: %s",
                        projectOperation.toString(),
                        projectTables.toString()));
                sampleString.append(this.databaseController.getFieldsInTable(projectTables.toString()));
            } else if (projectOperation.equals(ProjectOperations.Delete)) {
                sampleString.append(String.format("(Example: %s FROM %s WHERE field = some_field", projectOperation.toString(), projectTables.toString()));
            } else if (projectOperation.equals(ProjectOperations.UPDATE)) {
                sampleString.append(String.format("(Example: %s %s SET field = some_field WHERE field_id = some_field_id", projectOperation.toString(), projectTables.toString()));
            }
            consolePrinter.print(sampleString.toString());
            consolePrinter.printInLine("type:\\> ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String input = br.readLine().toLowerCase();
            int inputAfterValidation = consoleValidator.getInputNumber(input);
            if (inputAfterValidation == -2) {
                break;
            }
        }
        return "success";
    }

}
