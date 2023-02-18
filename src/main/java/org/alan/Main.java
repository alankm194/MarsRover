package org.alan;

import org.alan.controller.CompletePathController;
import org.alan.controller.Controller;
import org.alan.view.ConsoleOutput;
import org.alan.view.ConsoleUserInput;
import org.alan.view.Output;

public class Main {

    public static void main(String[] args) {
        do {
            try {
                Controller controller = new CompletePathController();
                Output output = new ConsoleOutput();
                controller.startController(new ConsoleUserInput().getUserInputData(), output);
                break;
            } catch (IllegalArgumentException E) {
                System.out.println("Vehicle is placed in an Illegal position. Please try again");
            }
        } while (true);
    }
}

