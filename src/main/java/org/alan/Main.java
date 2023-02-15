package org.alan;

import org.alan.controller.CompletePathController;
import org.alan.controller.Controller;
import org.alan.view.ConsoleOutput;
import org.alan.view.ConsoleUserInput;

public class Main {

    public static void main(String[] args) {
        Controller controller = new CompletePathController();
        ConsoleOutput output = new ConsoleOutput();
        controller.startController(new ConsoleUserInput().getUserInputData(), output);
    }
}

