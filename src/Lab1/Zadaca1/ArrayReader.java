package Lab1.Zadaca1;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayReader {

    public static IntegerArray readIntegerArray(InputStream input)
    {
        Scanner scanner = new Scanner(input);
        int arrayLength = scanner.nextInt();
        int[] array = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            array[i] = scanner.nextInt();
        }
        return new IntegerArray(array);
    }
}
