package org.example.service.kalkulacjaKwot;

import org.example.model.InputData;
import org.example.model.Rate;
import org.example.model.RateAmounts;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AmountsCalculationService implements IAmountsCalculationService {

    private static final BigDecimal YEAR = BigDecimal.valueOf(12);

    @Override
    public RateAmounts calculate(final InputData inputData) {
        switch (inputData.getRateType()) {
            case CONSTANT:
                return calculateConstantRate(inputData);
            case DECREASING:
                return calculateDecresingRate(inputData);
            default:
                throw new RuntimeException("Case not handled");
        }
    }

    @Override
    public RateAmounts calculate(final InputData inputData, Rate previousRate) {
        switch (inputData.getRateType()) {
            case CONSTANT:
                return calculateConstantRate(inputData, previousRate);
            case DECREASING:
                return calculateDescresingRate(inputData, previousRate);
            default:
                throw new RuntimeException("Case not handled");
        }
    }

    private RateAmounts calculateConstantRate(final InputData inputData) {
        BigDecimal q = calculatedQ(inputData.getInterestPercent());
        BigDecimal amount = inputData.getAmount();
        BigDecimal interestPercent = inputData.getInterestPercent();
        BigDecimal monthsDuration = inputData.getMonthsDuration();


        BigDecimal rateAmount = calculateConstantRateAmount(q, amount, monthsDuration);
        BigDecimal interestAmount = calculateInterestAmount(amount, interestPercent);
        BigDecimal capitalAmount = rateAmount.subtract(interestAmount);

        return new RateAmounts(rateAmount, interestAmount, capitalAmount);
    }


    private RateAmounts calculateConstantRate(final InputData inputData, final Rate previousRate) {

        BigDecimal q = calculatedQ(inputData.getInterestPercent());
        BigDecimal amount = inputData.getAmount();
        BigDecimal interestPercent = inputData.getInterestPercent();
        BigDecimal monthsDuration = inputData.getMonthsDuration();
        BigDecimal residualAmount = previousRate.getMortgageResidual().getAmount();


        BigDecimal rateAmount = calculateConstantRateAmount(q, amount, monthsDuration);
        BigDecimal interestAmount = calculateInterestAmount(residualAmount, interestPercent);
        BigDecimal capitalAmount = rateAmount.subtract(interestAmount);

        return new RateAmounts(rateAmount, interestAmount, capitalAmount);

    }

    private RateAmounts calculateDecresingRate(final InputData inputData) {

        BigDecimal amount = inputData.getAmount();
        BigDecimal monthsDuration = inputData.getMonthsDuration();
        BigDecimal interestPercent = inputData.getInterestPercent();

        BigDecimal interestAmount = calculateInterestAmount(amount, interestPercent);
        BigDecimal capitalAmount = calculateDecreasingCapitalAmount(amount, monthsDuration);
        BigDecimal rateAmount = capitalAmount.add(interestAmount);

        return new RateAmounts(rateAmount, interestAmount, capitalAmount);
    }

    private RateAmounts calculateDescresingRate(final InputData inputData, final Rate previousRate) {

        BigDecimal amount = previousRate.getMortgageResidual().getAmount();
        BigDecimal monthsDuration = inputData.getMonthsDuration();
        BigDecimal interestPercent = inputData.getInterestPercent();

        BigDecimal interestAmount = calculateInterestAmount(amount, interestPercent);
        BigDecimal capitalAmount = calculateDecreasingCapitalAmount(inputData.getAmount(), monthsDuration);
        BigDecimal rateAmount = capitalAmount.add(interestAmount);

        return new RateAmounts(rateAmount, interestAmount, capitalAmount);

    }

    private BigDecimal calculateConstantRateAmount(
            final BigDecimal q, final BigDecimal amount, final BigDecimal monthsDuration) {
        return amount
                .multiply(q.pow(monthsDuration.intValue()))
                .multiply(q.subtract(BigDecimal.ONE))
                .divide(q.pow(monthsDuration.intValue()).subtract(BigDecimal.ONE), 2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateInterestAmount(final BigDecimal amount, final BigDecimal interestPercent) {

        return amount.multiply(interestPercent).divide(YEAR, 10, RoundingMode.HALF_UP);

    }

    private BigDecimal calculateDecreasingCapitalAmount(final BigDecimal amount, final BigDecimal monthsDuration) {
        return amount.divide(monthsDuration, 10 ,RoundingMode.HALF_UP);
    }

    private BigDecimal calculatedQ(final BigDecimal interestPercent) {
        return interestPercent.divide(YEAR, 10, RoundingMode.HALF_UP).add(BigDecimal.ONE);
    }


}
