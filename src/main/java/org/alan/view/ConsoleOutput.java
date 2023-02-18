package org.alan.view;

import java.util.Arrays;

public class ConsoleOutput implements Output {
    @Override
    public void outputAnswer(String output, String message) {
        System.out.println(message);
        System.out.println(output);
    }
    @Override
    public void outputField(String[][] output, String message) {
        System.out.println(message);
        for(String[] row: output) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }
}
