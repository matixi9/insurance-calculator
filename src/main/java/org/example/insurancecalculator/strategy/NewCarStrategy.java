package org.example.insurancecalculator.strategy;

import org.example.insurancecalculator.model.InsuranceRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NewCarStrategy implements RiskCalculationStrategy{
    @Value("${insurance.risk.new-car}")
    private double newCarMultiplier;

    @Override
    public double calculateRisk(InsuranceRequest request) {
        if (request.getVehicleAge() < 5) {
            return newCarMultiplier;
        }
        return 1;
    }
}
