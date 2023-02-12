package org.alan.view;

import java.util.Scanner;
import java.util.Set;
import java.util.Arrays;
public class ConsoleUserInput {

    private static final Set<String> ACCEPTABLE_MOVEMENTS= Set.of("L", "M", "R");
    private static final Set<String> ACCEPTABLE_CARDINAL_POINTS = Set.of("N", "S", "E", "W");

    public static void main(String[] args) {
        ConsoleUserInput sdp = new ConsoleUserInput();
        sdp.getUserInputData();
    }

    public VehicleFieldDTO getUserInputData() {

        Scanner in = new Scanner(System.in);
        System.out.println("""
                Please enter Length and height dimensions to setup Plateau.
                an example input 5 4""");

        int[] fieldDimensions;
        int[] startCoordinatesVehicle;
        String cardinalDirection;

        fieldDimensions = getFieldDimensionInputData(in);
        System.out.println("Please enter Vehicle starting position");
        String[] vehicleData;
        do {
            vehicleData = getVehiclePositionInputData(in);
            try {
                startCoordinatesVehicle = parseVehicleCoordinates(vehicleData[0], vehicleData[1]);
            } catch (NumberFormatException E) {
                System.out.println("vehicle starting coordinates must be integer");
                continue;
            }
            if (!isVehicleInsideField(fieldDimensions, startCoordinatesVehicle)) {
                System.out.println("vehicle starting position must be within the confines of field");
                continue;
            }
            if (!(parseCardinalDirection(vehicleData[2]))) {
                System.out.println("Please enter a valid cardinal direction must be one of N, S, E, W. Please try again");
                continue;
            }
            cardinalDirection = vehicleData[2];
            break;
        } while (true);
        System.out.println("please enter movements for Mars Rover");
        String[] movements;
        do {
            movements = in.nextLine().trim().split("");
            if (!hasAllAcceptableMovementInstructions(movements)) {
                System.out.println("please enter valid movements for Mars Rover. Acceptable movements a L, R or M, e.g LMMRMMML");
                continue;
            }
            break;
        } while (true);
        return new VehicleFieldDTO(fieldDimensions, startCoordinatesVehicle, cardinalDirection, movements);
    }

    public int[] getFieldDimensionInputData(Scanner in) {
        int firstNumber;
        int secondNumber;
        do {
            try {
                var fieldDataArray = in.nextLine().trim().split(" ");
                if (fieldDataArray.length != 2) {
                    System.out.println("input length must be of length 2, example input 3 5. Please try again");
                    continue;
                }
                firstNumber = Integer.parseInt(fieldDataArray[0]);
                secondNumber = Integer.parseInt(fieldDataArray[1]);
                break;
            } catch (NumberFormatException E) {
                System.out.println("input must be 2 integers only, example input 3 5. Please try again");
            }
        } while (true);
        return new int[]{firstNumber, secondNumber};
    }

    private boolean isVehicleInsideField(int[] fieldData, int[] vehicleData) {
        if (0 > vehicleData[0] || vehicleData[0] >= fieldData[0])  {
            return false;
        }
        return 0 < vehicleData[1] && vehicleData[1] < fieldData[1];
    }

    private String[] getVehiclePositionInputData(Scanner in) {
        String[] vehicleData;
        do {
            try {
                String[] vehicleDataArray = in.nextLine().trim().split(" ");
                if (!(vehicleDataArray.length == 3)) {
                    System.out.println("input must consist of 3 inputs, consisting of 2 integers as starting position and one letter as Cardinal direction e.g 5 4 E");
                    continue;
                }
                vehicleData = new String[]{vehicleDataArray[0], vehicleDataArray[1], vehicleDataArray[2]};
                break;
            } catch (NumberFormatException E) {
                System.out.println("The first 2 inputs must be integers. Please try again.");
            }
        } while (true);
        return vehicleData;
    }

    private int[] parseVehicleCoordinates(String xPos, String yPos) throws NumberFormatException {
        return new int[]{Integer.parseInt(xPos), Integer.parseInt(yPos)};
    }

    private boolean parseCardinalDirection(String direction) {
        return ACCEPTABLE_CARDINAL_POINTS.contains(direction);
    }

    private boolean hasAllAcceptableMovementInstructions(String[] movementInstruction) {
        if (movementInstruction.length == 0) {
            return false;
        }
        return ACCEPTABLE_MOVEMENTS.containsAll(Arrays.asList(movementInstruction));
    }
}