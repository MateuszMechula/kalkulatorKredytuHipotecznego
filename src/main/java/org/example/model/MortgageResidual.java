package org.example.model;

import java.math.BigDecimal;

public class MortgageResidual {
    private final BigDecimal amount;

    private final BigDecimal duration;

    public MortgageResidual( BigDecimal amount, BigDecimal duration) {
        this.amount = amount;
        this.duration = duration;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "MortgageResidual{" +
                "amount=" + amount +
                ", duration=" + duration +
                '}';
    }
}
