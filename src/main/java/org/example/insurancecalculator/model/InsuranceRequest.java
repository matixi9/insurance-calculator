package org.example.insurancecalculator.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceRequest {
    @NotBlank(message = "PESEL cannot be empty")
    private String pesel;

    @Min(value = 18, message = "Driver has to be an adult")
    private int age;

    @NotNull(message = "Vehicle type is required")
    private VehicleType type;

    private boolean hadAccident;
}
