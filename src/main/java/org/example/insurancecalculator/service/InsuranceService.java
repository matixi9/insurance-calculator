package org.example.insurancecalculator.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.insurancecalculator.model.InsuranceOffer;
import org.example.insurancecalculator.model.InsuranceRequest;
import org.example.insurancecalculator.model.VehicleType;
import org.example.insurancecalculator.repository.InsuranceRepository;
import org.example.insurancecalculator.strategy.RiskCalculationStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Data
@Service
@RequiredArgsConstructor
public class InsuranceService {
    private final InsuranceRepository repository;
    private final List<RiskCalculationStrategy> strategies;

    @Value("${insurance.price.base}")
    private double basePrice;

    @Value("${insurance.price.vehicle.car}")
    private double carPrice;

    @Value("${insurance.price.vehicle.truck}")
    private double truckPrice;

    @Value("${insurance.price.vehicle.motorcycle}")
    private double motorcyclePrice;

    @Value("${insurance.price.vehicle.bike}")
    private double bikePrice;


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
        double price = basePrice;
        price += getPriceForVehicle(request.getType());

        for (RiskCalculationStrategy strategy : strategies) {
            double markup = strategy.calculateRisk(request);
            price *= markup;
        }
        return price;
    }

    private double getPriceForVehicle(VehicleType type) {
        return switch (type) {
            case CAR -> carPrice;
            case TRUCK -> truckPrice;
            case MOTORCYCLE -> motorcyclePrice;
            case BIKE -> bikePrice;
        };
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
