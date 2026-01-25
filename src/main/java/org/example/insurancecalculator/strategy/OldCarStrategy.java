package org.example.insurancecalculator.strategy;

import org.example.insurancecalculator.model.InsuranceRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OldCarStrategy implements RiskCalculationStrategy{
    @Value("${insurance.risk.old-car}")
    private double oldCarMultiplier;

    @Override
    public double calculateRisk(InsuranceRequest request) {
        if (request.getAge() > 20) {
            return oldCarMultiplier;
        }
        return 1;
    }
}
