package com.postech.lending.creditanalysis.service.strategy;

import java.math.BigDecimal;

public interface CalculationStrategy {
    BigDecimal calculate(BigDecimal desiredValue, int years);
}
