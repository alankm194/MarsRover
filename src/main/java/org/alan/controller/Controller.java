package org.alan.controller;

import org.alan.view.Output;
import org.alan.view.VehicleFieldDTO;

public interface Controller {

    void startController(VehicleFieldDTO vehicleFieldDTO, Output output);
}
