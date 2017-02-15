package ua.com.tervola.jdbc.consolepart;

/**
 * Created by user on 2/15/2017.
 */
public class ConsoleValidator {
    private static String COMMAND_EXIT = "exit";
    private static String COMMAND_QUIT = "quit";
    private static String TEXT_EMPTY = "";

    public int validateInput(String inputText) {

        if (inputText.trim().equals(TEXT_EMPTY)) {
            return -2;
        }

        if (inputText.equals(COMMAND_EXIT) || inputText.equals(COMMAND_QUIT)) {
            return 0;
        }

        try {
            return Integer.parseInt(inputText);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void parseResult(int number){

    }
}
