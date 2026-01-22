package org.example.insurancecalculator.repository;


import org.example.insurancecalculator.model.InsuranceOffer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class InsuranceRepository {
    private Map<Integer, InsuranceOffer> map = new HashMap<>();
    private int currentId = 1;

    public InsuranceOffer saveOffer(InsuranceOffer offer) {
        offer.setId(currentId);
        map.put(currentId, offer);
        currentId++;
        return offer;
    }

    public List<InsuranceOffer> findAll() {
        return new ArrayList<>(map.values());
    }

    public InsuranceOffer findById(int id) {
        return map.get(id);
    }

    public void deleteById(int id) {
        map.remove(id);
    }

    public void update(int id, InsuranceOffer offer) {
        map.put(id, offer);
    }
}
