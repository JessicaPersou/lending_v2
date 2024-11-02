package com.postech.lending.creditanalysis.service.strategy;


import java.math.BigDecimal;
import java.math.RoundingMode;

public class FourYearCalculation implements CalculationStrategy {

    private static final BigDecimal ANNUAL_INTEREST_RATE = new BigDecimal("0.1");

    @Override
    public BigDecimal calculate(BigDecimal desiredValue, int years) {
        BigDecimal annualInterest = ANNUAL_INTEREST_RATE.multiply(BigDecimal.valueOf(4));
        return desiredValue.multiply(BigDecimal.ONE.add(annualInterest)).setScale(2, RoundingMode.HALF_UP);
    }
}


