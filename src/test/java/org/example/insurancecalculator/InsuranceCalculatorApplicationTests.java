package org.example.insurancecalculator;

import org.example.insurancecalculator.model.InsuranceRequest;
import org.example.insurancecalculator.model.VehicleType;
import org.example.insurancecalculator.repository.InsuranceRepository;
import org.example.insurancecalculator.service.InsuranceService;
import org.example.insurancecalculator.strategy.RiskCalculationStrategy;
import org.example.insurancecalculator.strategy.YoungDriverStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import org.example.insurancecalculator.model.InsuranceRequest;
import org.example.insurancecalculator.model.VehicleType;
import org.example.insurancecalculator.repository.InsuranceRepository;
import org.example.insurancecalculator.service.InsuranceService;
import org.example.insurancecalculator.strategy.RiskCalculationStrategy;
import org.example.insurancecalculator.strategy.YoungDriverStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class InsuranceCalculatorApplicationTests {

        @Test
        void shouldCalculateCorrectPriceForYoungDriverWithCar() {
            InsuranceRepository repository = new InsuranceRepository();
            List<RiskCalculationStrategy> strategies = new ArrayList<>();
            strategies.add(new YoungDriverStrategy());

            InsuranceService service = new InsuranceService(repository, strategies);

            InsuranceRequest request = new InsuranceRequest("12345678901", 20, VehicleType.CAR, 1.6, 10,false);

            // Act
            // Base: 300 + Car: 540 = 840.
            // Young driver markup: 1.3 -> 840 * 1.3 = 1092.0
            double expectedPrice = 1092.0;

            var offer = service.calculateAndSave(request);

            Assertions.assertEquals(expectedPrice, offer.getFinalPrice(), 0.01);
        }
    }
