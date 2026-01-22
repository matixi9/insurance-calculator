package org.example.insurancecalculator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsuranceOffer {
    private int id;
    private double finalPrice;
    private String description;
    private LocalDate creationDate;


}
