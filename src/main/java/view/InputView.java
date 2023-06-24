package view;

import constant.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    public static List<String> inputNames() {
        String[] input = scanner.nextLine().split(",");
        List<String> result = new ArrayList<>();
        result.add(input[0]);
        result.add(input[1]);
        return result;
    }

    public static int inputMoney() {
        return scanner.nextInt();
    }
}
