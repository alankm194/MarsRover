package org.alan.view;

import java.util.Arrays;

public class ConsoleOutput {

    public void outputAnswerToConsole(String output, String message) {
        System.out.println(message);
        System.out.println(output);
    }

    public void outputFieldToConsole(String[][] output, String message) {
        System.out.println(message);
        for(String[] row: output) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }
}
