package org.example.service.kalkulacjaKwot;

import org.example.model.InputData;
import org.example.model.Rate;
import org.example.model.RateAmounts;

public interface IAmountsCalculationService {
    RateAmounts calculate(final InputData inputData);

    RateAmounts calculate(InputData inputData, Rate previousRate);
}
