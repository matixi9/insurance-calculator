package org.example.insurancecalculator.strategy;

import org.example.insurancecalculator.model.InsuranceRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AccidentHistoryStrategy implements RiskCalculationStrategy {
    @Value("${insurance.risk.accident}")
    private double accidentMultiplier;

    @Override
    public double calculateRisk(InsuranceRequest request) {
        if (request.isHadAccident()) {
            return accidentMultiplier;
        }
        return 1;
    }
}
