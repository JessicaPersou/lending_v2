package com.postech.lending.creditanalysis.service.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OneYearCalculation implements CalculationStrategy {

    private static final BigDecimal ANNUAL_INTEREST_RATE = new BigDecimal("0.025");

    @Override
    public BigDecimal calculate(BigDecimal desiredValue, int years) {
        BigDecimal annualInterest = ANNUAL_INTEREST_RATE.multiply(BigDecimal.valueOf(1));
        return desiredValue.multiply(BigDecimal.ONE.add(annualInterest)).setScale(2, RoundingMode.HALF_UP);
    }

}