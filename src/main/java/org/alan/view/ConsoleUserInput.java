package org.alan.view;

import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Arrays;
public class ConsoleUserInput {

    private static final Set<String> ACCEPTABLE_MOVEMENTS= Set.of("L", "M", "R");
    private static final Set<String> ACCEPTABLE_CARDINAL_POINTS = Set.of("N", "S", "E", "W");
    private static final Set<String> ACCEPTABLE_VEHICLE_TYPES = Set.of("Knight Rover", "Mars Rover");

    private static final Set<String> ACCEPTABLE_FIELD_TYPES = Set.of("Circle", "Rectangle");


    public VehicleFieldDTO getUserInputData() {

        Scanner in = new Scanner(System.in);

        int[] startCoordinatesVehicle;
        String fieldType = getFieldType(in);

        int[] fieldDimensions = getFieldDimensionInputData(in);

        System.out.println("Please enter Vehicle starting position");
        String[] vehicleData;
        String cardinalDirection;
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
            if (!(ACCEPTABLE_CARDINAL_POINTS.contains(vehicleData[2]))) {
                System.out.println("Please enter a valid cardinal direction must be one of N, S, E, W. Please try again");
                continue;
            }
            cardinalDirection = vehicleData[2];
            break;
        } while (true);

        String vehicleType  = getVehicleType(in);
        List<String> ListOfMovements = getListOfMovements(in);
        return new VehicleFieldDTO(fieldDimensions,
                startCoordinatesVehicle,
                cardinalDirection,
                ListOfMovements,
                vehicleType,
                fieldType);
    }


    private List<String> getListOfMovements(Scanner in) {
        System.out.println("please enter movements for vehicle");
        String[] movements;
        do {
            movements = in.nextLine().trim().split("");
            if (!hasAllAcceptableMovementInstructions(movements)) {
                System.out.println("please enter valid movements for Mars Rover. Acceptable movements a L, R or M, e.g LMMRMMML");
                continue;
            }
            break;
        } while (true);
        return Arrays.asList(movements);
    }
    private String getFieldType(Scanner in) {
        String fieldType;
        System.out.println("Please choose a Field, your options are Rectangle or Circle");
        do {
            fieldType = in.nextLine();
            if (!ACCEPTABLE_FIELD_TYPES.contains(fieldType)) {
                System.out.println("Please enter either \"Rectangle\" or \"Circle\" exactly ");
                continue;
            }
            break;
        } while(true);
        return fieldType;
    }
    private String getVehicleType(Scanner in) {
        String vehicleType;
        System.out.println("Please choose a vehicle, your options are Mars Rover or Knight Rover");
        do {
            vehicleType = in.nextLine();
            if (!ACCEPTABLE_VEHICLE_TYPES.contains(vehicleType)) {
                System.out.println("Please enter either \"Knight Rover\" or \"Mars Rover\" exactly ");
                continue;
            }
            break;
        } while(true);
        return vehicleType;
    }
    private int[] getFieldDimensionInputData(Scanner in) {
        System.out.println("""
                Please enter Length and height dimensions to setup Plateau.
                an example input 5 4""");
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


    private boolean hasAllAcceptableMovementInstructions(String[] movementInstruction) {
        if (movementInstruction.length == 0) {
            return false;
        }
        return ACCEPTABLE_MOVEMENTS.containsAll(Arrays.asList(movementInstruction));
    }
}
