package org.example.insurancecalculator.strategy;

import org.example.insurancecalculator.model.InsuranceRequest;
import org.springframework.stereotype.Component;

@Component
public class AccidentHistoryStrategy implements RiskCalculationStrategy {
    @Override
    public double calculateRisk(InsuranceRequest request) {
        if (request.isHadAccident()) {
            return 1.6;
        }
        return 1;
    }
}
