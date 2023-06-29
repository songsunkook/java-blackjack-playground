package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final String agree = "y";
    private static final String disagree = "n";
    private static final String splitter = ",";
    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> inputNames() {
        String[] input = scanner.nextLine().split(splitter);
        List<String> result = new ArrayList<>();
        result.add(input[0]);
        result.add(input[1]);
        return result;
    }

    public static int inputMoney() {
        return scanner.nextInt();
    }

    public static boolean inputOneMoreCard() {
        return scanner.next().equals(agree);
    }
}
