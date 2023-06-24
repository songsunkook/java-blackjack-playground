package view;

import java.util.Arrays;

public class OutputView {
    public static void println() {
        System.out.println();
    }

    public static void println(String message) {
        System.out.println(message);
    }

    public static void println(Object... args) {
        for (Object obj : args) {
            System.out.print(obj + " ");
        }
        System.out.println();
    }
}
