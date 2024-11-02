package com.postech.lending.creditanalysis.service.strategy;


import java.math.BigDecimal;
import java.math.RoundingMode;

public class TwoYearCalculation implements CalculationStrategy {

    private static final BigDecimal ANNUAL_INTEREST_RATE = new BigDecimal("0.050");

    @Override
    public BigDecimal calculate(BigDecimal desiredValue, int years) {
        // Implementação para 2 anos
        BigDecimal annualInterest = ANNUAL_INTEREST_RATE.multiply(BigDecimal.valueOf(2));
        return desiredValue.multiply(BigDecimal.ONE.add(annualInterest)).setScale(2, RoundingMode.HALF_UP);
    }
}


