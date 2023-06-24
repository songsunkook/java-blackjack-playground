package view;

import java.util.Arrays;

public class OutputView {
    public static void println(Object... args) {
        print(args);
        System.out.println();
    }

    public static void print(Object... args) {
        for (Object obj : args) {
            System.out.print(obj + " ");
        }
    }
}
