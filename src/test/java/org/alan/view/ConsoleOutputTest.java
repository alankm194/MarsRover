package org.alan.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsoleOutputTest {
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
        System.setProperty("line.separator", "\r\n");
    }

    @Test
    public void outputAnswerToConsole() {
        ConsoleOutput consoleOutput = new ConsoleOutput();
        consoleOutput.outputAnswer("This is my test", "test message");
        assertEquals("""
                        test message
                        This is my test""".replace("\n", "\r\n"),
                outputStreamCaptor.toString().trim());
    }

    @Test
    public void testOutputFieldToConsole() {
        ConsoleOutput consoleOutput = new ConsoleOutput();
        String[][] testArray = {
                {" 0", " 1", "2"},
                {"77", "56", " 44"},
        };
        consoleOutput.outputField(testArray, "test message");
        assertEquals("test message" + "\r\n"
                        + Arrays.toString(testArray[0]) + "\r\n"
                        + Arrays.toString(testArray[1]),
                outputStreamCaptor.toString().trim());
    }

}
