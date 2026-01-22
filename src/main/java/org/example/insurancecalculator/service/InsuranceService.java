package org.example.insurancecalculator.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.insurancecalculator.model.InsuranceOffer;
import org.example.insurancecalculator.model.InsuranceRequest;
import org.example.insurancecalculator.model.VehicleType;
import org.example.insurancecalculator.repository.InsuranceRepository;
import org.example.insurancecalculator.strategy.RiskCalculationStrategy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Data
@Service
@RequiredArgsConstructor
public class InsuranceService {
    private final InsuranceRepository repository;
    private final List<RiskCalculationStrategy> strategies;

    public InsuranceOffer calculateAndSave(InsuranceRequest request){
        InsuranceOffer offer = new InsuranceOffer();
        offer.setFinalPrice(calculatePrice(request));
        offer.setDescription("Offer calculated for driver with PESEL: " + request.getPesel());
        offer.setCreationDate(LocalDate.now());

        return repository.saveOffer(offer);
    }

    public InsuranceOffer updateOffer(int id, InsuranceRequest request) {
        InsuranceOffer existingOffer = repository.findById(id);
        if (repository.findById(id) == null) {
            throw new IllegalArgumentException("Offer with ID " + id + " does not exist!");
        }

        double newPrice = calculatePrice(request);
        existingOffer.setFinalPrice(newPrice);
        existingOffer.setDescription("Updated offer for driver with PESEL: " + request.getPesel());

        repository.update(id, existingOffer);
        return existingOffer;
    }

    private double calculatePrice(InsuranceRequest request) {
        double price = 300;

        price += request.getType().getBasePrice();


        for (RiskCalculationStrategy strategy : strategies) {
            double markup = strategy.calculateRisk(request);
            price *= markup;
        }
        return price;
    }

    public List<InsuranceOffer> getAllOffers() {
        return repository.findAll();
    }

    public InsuranceOffer getOffer(int id) {
        return repository.findById(id);
    }

    public void deleteOffer(int id) {
        repository.deleteById(id);
    }
}
