package org.alan.controller;

import org.alan.view.ConsoleOutput;
import org.alan.view.VehicleFieldDTO;

public interface Controller {

    void startController(VehicleFieldDTO vehicleFieldDTO, ConsoleOutput output);
}
