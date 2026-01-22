package org.example.insurancecalculator.strategy;

import org.example.insurancecalculator.model.InsuranceRequest;
import org.springframework.stereotype.Component;

@Component
public class YoungDriverStrategy implements RiskCalculationStrategy {
    @Override
    public double calculateRisk(InsuranceRequest request) {
        if (request.getAge() < 24) {
            return 1.3;
        }
        return 1;
    }
}
