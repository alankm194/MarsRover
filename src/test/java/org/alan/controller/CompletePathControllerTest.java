package org.alan.controller;

import org.alan.model.vehicle.CardinalPoint;
import org.alan.model.vehicle.KnightVehicle;
import org.alan.model.vehicle.MarsRover;
import org.alan.model.vehicle.Vehicle;
import org.alan.view.ConsoleOutput;
import org.alan.view.VehicleFieldDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.ArgumentCaptor;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CompletePathControllerTest {


    private final static String[][] fieldArrayExpected1 =  {
        {"-1", "-1", "-1",  " 0",  " 0",  " 0",  " 0", "-1", "-1", "-1" },
        {"-1", "-1", " 0",  " 0",  " 0",  " 0",  " 0", " 0", "-1", "-1" },
        {"-1", " 0", " 0",  " 0",  " 0",  " 0",  " 0", " 0", " 0", "-1" },
        {" 0", " 0", " 2",  " 0",  " 0",  " 0",  " 0", " 0", " 0", " 0" },
        {" 0", " 0", " 0",  " 0",  " 0",  " 0",  " 0", " 0", " 0", " 0" },
        {" 0", " 0", " 0",  " 1",  " 0",  " 0",  " 0", " 0", " 0", " 0" },
        {" 0", " 0", " 0",  " 0",  " 0",  " 0",  " 0", " 0", " 0", " 0" },
        {"-1", " 0", " 0",  " 0",  " 0",  " 0",  " 0", " 0", " 0", "-1" },
        {"-1", "-1", " 0",  " 0",  " 0",  " 0",  " 0", " 0", "-1", "-1" },
        {"-1", "-1", "-1",  " 0",  " 0",  " 0",  " 0", "-1", "-1", "-1" },
    };

    private final static String[][] fieldArrayExpected2 = {
        {" 0",  " 0",  " 0",  " 0"},
        {" 0",  " 0",  " 0",  " 0"},
        {" 0",  " 0",  " 0",  " 0"},
        {" 0",  " 0",  " 0",  " 0"},
        {" 0",  " 0",  " 0",  " 0"},
        {" 0",  " 0",  " 1",  " 2"},
        {" 0",  " 0",  " 0",  " 0"},
        {" 0",  " 0",  " 0",  " 0"},
    };

    private final static String[][] fieldArrayExpected3 = {

            {" 0",  " 0",  " 0",  " 0",  " 0",  " 0",  " 0"},
            {" 0",  " 0",  " 0",  " 0",  " 0",  " 0",  " 0"},
            {" 0",  " 0",  " 0",  " 0",  " 0",  " 0",  " 0"},
            {" 0",  " 0",  " 0",  " 0",  " 0",  " 0",  " 0"},
            {" 0",  " 0",  " 1",  " ???",  " ???",  " 2",  " 0"},
            {" 0",  " 0",  " 0",  " 0",  " 0",  " 0",  " 0"},
            {" 0",  " 0",  " 0",  " 0",  " 0",  " 0",  " 0"}
    };

    private static final Map<String, String[][]> fieldArrayExpectedMap = Map.of(
            "fieldArrayExpected1", fieldArrayExpected1,
            "fieldArrayExpected2", fieldArrayExpected2,
            "fieldArrayExpected3", fieldArrayExpected3
    );


    @Test
    public void testControllerForACompletePath() {
        int rowLength = 7;
        int colLength = 6;
        CompletePathController controller = new CompletePathController();
        controller.setField(rowLength, colLength, "Rectangle"); //6,7
        Vehicle vehicle = new MarsRover(CardinalPoint.EAST);
        var movementInstructions = List.of("M", "M", "L", "M", "M", "R", "M", "M");
        String finalLocation = controller.getFinalLocationForVehicle(vehicle, movementInstructions, 0, 0);
        assertEquals("4 2 E", finalLocation);
    }

    @Test
    public void testControllerIgnoresCommandsThatLeadsOutOfBounds() {
        int lengthX = 3;
        int lengthY = 3;
        CompletePathController controller = new CompletePathController();
        controller.setField(lengthX, lengthY, "Rectangle");
        Vehicle vehicle = new MarsRover(CardinalPoint.EAST);
        var movementInstructions = List.of("M", "M", "M", "M");
        String finalLocation = controller.getFinalLocationForVehicle(vehicle, movementInstructions, 0, 0);
        assertEquals("2 0 E", finalLocation);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/completeControllerKnightVehicleTest.csv", numLinesToSkip = 1)
    public void testKnightVehicle(String currDir, String expectedDir, int expectedXPos, int expectedYPos) {
        int lengthX = 15;
        int lengthY = 15;
        CardinalPoint expectedDirection = CardinalPoint.valueOf(expectedDir);
        CompletePathController controller = new CompletePathController();
        controller.setField(lengthX, lengthY, "Rectangle");
        Vehicle vehicle = new KnightVehicle(CardinalPoint.valueOf(currDir));
        var movementInstructions = List.of("M");
        String finalLocation = controller.getFinalLocationForVehicle(vehicle, movementInstructions, 5, 5);
        assertEquals(String.format("%d %d %s", expectedXPos, expectedYPos, expectedDirection.getInitial()),
                finalLocation);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/completePathFullTestForFinalLocation.csv", numLinesToSkip = 1)
    public void testStartControllerOutputsCorrectFinalLocation(int fieldRowLength,
                                    int fieldColLength,
                                    int vehicleStartX,
                                    int vehicleStartY,
                                    String cardinalInitial,
                                    String movements,
                                    String vehicleType,
                                    String fieldType,
                                    String expectedOutput) {

        VehicleFieldDTO vehicleFieldDTO = new VehicleFieldDTO(
                new int[]{fieldRowLength, fieldColLength},
                new int[]{vehicleStartX, vehicleStartY},
                cardinalInitial,
                Arrays.asList(movements.split("")),
                vehicleType,
                fieldType
        );

        CompletePathController controller = new CompletePathController();
        ConsoleOutput mockedOutput = mock(ConsoleOutput.class);
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        controller.startController(vehicleFieldDTO, mockedOutput);

        verify(mockedOutput, times(1)).outputAnswer(argumentCaptor.capture(), any());
        assertEquals(expectedOutput, argumentCaptor.getValue());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/completePathFullTestFor2dArrayPrintToConsole.csv", numLinesToSkip = 1)
    public void testStartControllerOutputsCorrect2dArray(int fieldRowLength,
                                                               int fieldColLength,
                                                               int vehicleStartX,
                                                               int vehicleStartY,
                                                               String cardinalInitial,
                                                               String movements,
                                                               String vehicleType,
                                                               String fieldType,
                                                               String expectedKey) {

        VehicleFieldDTO vehicleFieldDTO = new VehicleFieldDTO(
                new int[]{fieldRowLength, fieldColLength},
                new int[]{vehicleStartX, vehicleStartY},
                cardinalInitial,
                Arrays.asList(movements.split("")),
                vehicleType,
                fieldType
        );

        CompletePathController controller = new CompletePathController();
        ConsoleOutput mockedOutput = mock(ConsoleOutput.class);
        ArgumentCaptor<Object> argumentCaptor = ArgumentCaptor.forClass(Object.class);
        controller.startController(vehicleFieldDTO, mockedOutput);

        verify(mockedOutput, times(1)).outputField((String[][])argumentCaptor.capture(), any());
        assertArrayEquals(fieldArrayExpectedMap.get(expectedKey), (String[][])argumentCaptor.getValue());
    }

    @Test
    public void whenVehicleIsPlacedONOutOfBoundMark_thenThrowIllegalArgumentException() {
        VehicleFieldDTO vehicleFieldDTO = new VehicleFieldDTO(
                new int[]{3, 3},
                new int[]{0, 0},
                "N",
                List.of("M"),
                "Mars Rover",
                "Circle"
        );
        CompletePathController controller = new CompletePathController();


        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> controller.startController(vehicleFieldDTO, new ConsoleOutput()),
                "test if vehicle is placed on Out of bound mark throw exception"
        );

        assertEquals("Vehicle is set in an illegal place", thrown.getMessage());
    }



}
