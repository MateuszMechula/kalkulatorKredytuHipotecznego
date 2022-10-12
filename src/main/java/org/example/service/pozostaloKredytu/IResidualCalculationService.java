package org.example.service.pozostaloKredytu;

import org.example.model.InputData;
import org.example.model.MortgageResidual;
import org.example.model.Rate;
import org.example.model.RateAmounts;

public interface IResidualCalculationService {
    MortgageResidual calculate(final RateAmounts rateAmounts, final InputData inputData);

    MortgageResidual calculate(RateAmounts rateAmounts, Rate previousRate);

}
