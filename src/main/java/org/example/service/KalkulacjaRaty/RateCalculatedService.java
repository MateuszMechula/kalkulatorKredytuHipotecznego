package org.example.service.KalkulacjaRaty;

import org.example.model.*;
import org.example.service.pozostaloKredytu.IResidualCalculationService;
import org.example.service.okresKredytowania.ITimePointService;
import org.example.service.kalkulacjaKwot.IAmountsCalculationService;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class RateCalculatedService implements IRateCalculatedService {

    private final ITimePointService ITimePointService;

    private final IAmountsCalculationService IAmountsCalculationService;

    private final IResidualCalculationService IResidualCalculationService;

    public RateCalculatedService(
            ITimePointService ITimePointService,
            IAmountsCalculationService IAmountsCalculationService,
            IResidualCalculationService IResidualCalculationService) {

        this.ITimePointService = ITimePointService;
        this.IAmountsCalculationService = IAmountsCalculationService;
        this.IResidualCalculationService = IResidualCalculationService;
    }

    @Override
    public List<Rate> calculate(final InputData inputData) {
        List<Rate> rates = new LinkedList<>();

        BigDecimal rateNumber = BigDecimal.ONE;

        Rate firstRate = calculateFirstRate(rateNumber, inputData);

        rates.add(firstRate);

        Rate previousRate = firstRate;

        for (BigDecimal index = rateNumber.add(BigDecimal.ONE);
             index.compareTo(inputData.getMonthsDuration()) <= 0;
             index = index.add(BigDecimal.ONE)) {

            Rate nextRate = calculateNextRate(index, inputData,previousRate);
            rates.add(nextRate);
            previousRate = nextRate;

        }

        return rates;

    }

    private Rate calculateFirstRate(BigDecimal rateNumber, InputData inputData) {
        TimePoint timePoint = ITimePointService.calculate(rateNumber, inputData);
        RateAmounts rateAmounts = IAmountsCalculationService.calculate(inputData);
        MortgageResidual mortgageResidual = IResidualCalculationService.calculate(rateAmounts, inputData);

        return new Rate(rateNumber, timePoint, rateAmounts, mortgageResidual);
    }

    private Rate calculateNextRate(BigDecimal rateNumber, InputData inputData, Rate previousRate) {
        TimePoint timePoint = ITimePointService.calculate(rateNumber, inputData);
        RateAmounts rateAmounts = IAmountsCalculationService.calculate(inputData, previousRate);
        MortgageResidual mortgageResidual = IResidualCalculationService.calculate(rateAmounts, previousRate);

        return new Rate(rateNumber, timePoint, rateAmounts, mortgageResidual);
    }
}
