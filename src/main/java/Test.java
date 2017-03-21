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
       String s = "sdfd,sdfsdf,sdfd";

        String[] split = s.split(",");

        System.out.println(split.length);

    }
}
