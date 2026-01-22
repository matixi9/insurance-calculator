package org.example.insurancecalculator.strategy;

import org.example.insurancecalculator.model.InsuranceRequest;

public interface RiskCalculationStrategy {
    double calculateRisk(InsuranceRequest request);
}
