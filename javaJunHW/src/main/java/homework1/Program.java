package homework1;

import java.util.Arrays;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        List<Number> numList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        int[] result = new int[1];
        numList.stream()
                .filter(n -> ((int)n % 2) == 0)
                .forEach(n -> result[0] += (int)n);
        System.out.println("result: " + result[0]);
    }
}
