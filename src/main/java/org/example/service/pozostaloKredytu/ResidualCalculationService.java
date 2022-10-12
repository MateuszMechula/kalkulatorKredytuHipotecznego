package org.example.service.pozostaloKredytu;

import org.example.model.InputData;
import org.example.model.MortgageResidual;
import org.example.model.Rate;
import org.example.model.RateAmounts;
import java.math.BigDecimal;

public class ResidualCalculationService implements IResidualCalculationService {
    @Override
    public MortgageResidual calculate(final RateAmounts rateAmounts, final InputData inputData) {
        BigDecimal residualAmount = inputData.getAmount().subtract(rateAmounts.getRateAmount());
        BigDecimal residualDuration = inputData.getMonthsDuration().subtract(BigDecimal.ONE);

        return new MortgageResidual(residualAmount, residualDuration);
    }

    @Override
    public MortgageResidual calculate(final RateAmounts rateAmounts, final Rate previousRate) {
        MortgageResidual residual = previousRate.getMortgageResidual();

        BigDecimal residualAmount = residual.getAmount().subtract(rateAmounts.getCapitalAmount());
        BigDecimal residualDuration = residual.getDuration().subtract(BigDecimal.ONE);

        return new MortgageResidual(residualAmount, residualDuration);
    }
}
