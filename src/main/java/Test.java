import java.util.*;

/**
 * Created by user on 12/7/2016.
 */
public class Test {


    private static String INPUT_TEXT = "Input number";
    private static String INPUT_SPACE = "        ";
    private static int LETTER_HIGHT = 4;
    private static List<String> ONE = Arrays.asList(
            "  *",
            "* *",
            "  *",
            "  *",
            "  *");
    private static List<String> TWO = Arrays.asList(
            "  * *  ",
            "*    * ",
            "   *   ",
            " *     ",
            "* * * *");
    private static List<String> THREE = Arrays.asList(
            "  * * ",
            "    * ",
            "   *  ",
            "*   * ",
            "  * * ");
    private static List<String> FOUR = Arrays.asList(
            "*     *",
            "*     *",
            "* * * *",
            "      *",
            "      *");



    public static void main(String[] args) {

        String numberFromInput = getTextFromConsole();

        List<List<String>> list = defineNumber(numberFromInput);


        for (int i = 0; i <= LETTER_HIGHT; i++) {

            for (List<String> strings : list) {
                System.out.print(strings.get(i));
                System.out.print(INPUT_SPACE);

            }
            System.out.print("\n");
        }
    }

    private static List defineNumber(String numberFromInput) {
        List<List<String>> resultList = new ArrayList<>();
        for (char c : numberFromInput.toCharArray()) {
            switch (Character.getNumericValue(c)) {
                case 1:
                    resultList.add(ONE);
                    break;
                case 2:
                    resultList.add(TWO);
                    break;
                case 3:
                    resultList.add(THREE);
                    break;
                case 4:
                    resultList.add(FOUR);
                    break;
//                case 5:
//                    resultList.add(TWO);
//                case 6:
//                    resultList.add(TWO);
//                case 7:
//                    resultList.add(TWO);
//                case 8:
//                    resultList.add(TWO);
//                case 9:
//                    resultList.add(TWO);
//                case 0:
//                    resultList.add(TWO);
            }
        }
        return resultList;

    }

    private static String getTextFromConsole() {
        System.out.println("Input number:");
        while (true) {
            Scanner in = new Scanner(System.in);
            if (in.hasNextBigInteger()) {
                return in.nextLine();
            } else {
                System.out.println(INPUT_TEXT + "again");
            }
        }
    }
}
