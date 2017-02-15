package ua.com.tervola.jdbc.consolepart;

/**
 * Created by user on 2/15/2017.
 */
public class ConsoleValidator {
    private static String COMMAND_EXIT = "exit";
    private static String COMMAND_QUIT = "quit";
    private static String TEXT_EMPTY = "";

    private static int INPUT_ENTER = -1;
    private static int INPUT_EXIT = -2;
    private static int INPUT_WRONG_FORMAT = -3;

    public int validateInput(String inputText) {

        if (inputText.trim().equals(TEXT_EMPTY)) {
            return INPUT_ENTER;
        }

        if (inputText.equals(COMMAND_EXIT) || inputText.equals(COMMAND_QUIT)) {
            return INPUT_EXIT;
        }

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

}
