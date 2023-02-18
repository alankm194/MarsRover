package org.alan.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsoleInputTest {

    private ConsoleUserInput userInput;

    @BeforeEach
    public void beforeTest() {
        userInput = new ConsoleUserInput();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/UserInputTest.csv")
    public void test(String input) {
        String[] expectedInput = input.split(System.lineSeparator());
        int[] expectedFieldDimensions = Arrays
                .stream(expectedInput[1].split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        String[] vehiclePos = expectedInput[2].split(" ");
        int[] expectedVehiclePosition = new int[] {
                Integer.parseInt(vehiclePos[0]),
                Integer.parseInt(vehiclePos[1]),

        };

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        VehicleFieldDTO expected = new VehicleFieldDTO(
                expectedFieldDimensions,
                expectedVehiclePosition,
                vehiclePos[2],
                Arrays.asList(expectedInput[4].split("")),
                expectedInput[3],
                expectedInput[0]
        );


        assertEquals(expected, userInput.getUserInputData());
    }

    @ParameterizedTest
    @CsvSource({"M", "L", "R", "LR", "RL", "LRM", "MMM", "LRLRMMMLRLMMMMLMRM"})
    public void testGettingMovementsFromUser(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner mockScanner = new Scanner(System.in);

        assertEquals(Arrays.asList(input.trim().split("")), userInput.getListOfMovements(mockScanner));
    }

    @ParameterizedTest
    @CsvSource({"Circle", "Rectangle"})
    public void testGettingFieldTypesFromUser(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner mockScanner = new Scanner(System.in);
        assertEquals(input, userInput.getFieldType(mockScanner));
    }

    @ParameterizedTest
    @CsvSource({"Mars Rover", "Knight Rover"})
    public void testGettingVehicleTypesFromUser(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner mockScanner = new Scanner(System.in);

        assertEquals(input, userInput.getVehicleType(mockScanner));
    }

    @ParameterizedTest
    @CsvSource({"3 3", " 300 300", "23 43", "56 56", "100 200"} )
    public void testGettingFieldDimensionFromUser(String input) {

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner mockScanner = new Scanner(System.in);
        int[] expectedField = Arrays
                .stream(input.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        assertArrayEquals(expectedField, userInput.getFieldDimensionInputData(mockScanner));
    }
    @ParameterizedTest
    @CsvSource({"3 3 E", "12 12 E", "44 44 W", "100 102 N", "300 300 S"})
    public void testGettingVehiclePositionsFromUser(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Scanner mockScanner = new Scanner(System.in);

        assertArrayEquals(input.split(" "), userInput.getVehiclePositionInputData(mockScanner));
    }
}
