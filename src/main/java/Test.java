import java.io.File;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by user on 12/7/2016.
 */
public class Test {
    public static void main(String[] args) {
        Path path = Paths.get("C:\\tmp\\");
        File myDirectory = path.toFile();
        for (File file : myDirectory.listFiles()) {
            String fileName = file.getName();
            if ("1.txt".equals(fileName)) {
                System.out.println("bingo");
            }
        }


    }
}
