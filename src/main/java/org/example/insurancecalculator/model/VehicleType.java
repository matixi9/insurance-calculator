package org.example.insurancecalculator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VehicleType {
    CAR(540.0),
    TRUCK(750.0),
    MOTORCYCLE(300.0),
    BIKE(50.0);

    private final double basePrice;
}