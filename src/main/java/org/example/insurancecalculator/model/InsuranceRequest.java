package org.example.insurancecalculator.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.pl.PESEL;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceRequest {
    @NotNull(message = "PESEL is required")
    @PESEL(message = "PESEL is invalid")
    private String pesel;

    @Min(value = 18, message = "Driver has to be an adult")
    private int age;

    @NotNull(message = "Vehicle type is required")
    private VehicleType type;

    private double engineCapacity;

    @NotNull(message = "Car age is required")
    private int vehicleAge;

    private boolean hadAccident;
}
