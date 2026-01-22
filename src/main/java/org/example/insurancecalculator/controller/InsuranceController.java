package org.example.insurancecalculator.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.insurancecalculator.model.InsuranceOffer;
import org.example.insurancecalculator.model.InsuranceRequest;
import org.example.insurancecalculator.service.InsuranceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/insurance")
@RequiredArgsConstructor
public class InsuranceController {
    private final InsuranceService service;

    @GetMapping
    public List<InsuranceOffer> getAll() {
        return service.getAllOffers();
    }
    @GetMapping("/{id}")
    public InsuranceOffer getById(@PathVariable int id) {
        return service.getOffer(id);
    }
    @PostMapping
    public InsuranceOffer create(@Valid @RequestBody InsuranceRequest request) {
        return service.calculateAndSave(request);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.deleteOffer(id);
    }
    @PutMapping("/{id}")
    public InsuranceOffer update(@PathVariable int id, @Valid @RequestBody InsuranceRequest request) {
        return service.updateOffer(id,request);
    }

}
