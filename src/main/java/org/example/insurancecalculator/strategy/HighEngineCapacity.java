package org.example.insurancecalculator.strategy;

import org.example.insurancecalculator.model.InsuranceRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HighEngineCapacity implements RiskCalculationStrategy{
    @Value("${insurance.risk.high-engine-capacity}")
    private double highEngineCapacityMultiplier;

    @Override
    public double calculateRisk(InsuranceRequest request) {
        if (request.getEngineCapacity() >= 3) {
            return highEngineCapacityMultiplier;
        }
        return 1;
    }
}
