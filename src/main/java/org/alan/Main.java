package org.alan;

import org.alan.controller.CompletePathController;
import org.alan.controller.Controller;
import org.alan.view.ConsoleOutput;
import org.alan.view.ConsoleUserInput;

public class Main {

    public static void main(String[] args) {
        do {
            try {
                Controller controller = new CompletePathController();
                ConsoleOutput output = new ConsoleOutput();
                controller.startController(new ConsoleUserInput().getUserInputData(), output);
                break;
            } catch (IllegalArgumentException E) {
                System.out.println("Vehicle is placed in an Illegal position. Please try again");
            }
        } while (true);
    }
}

