package com.postech.lending.creditanalysis.service.util;

import static java.text.MessageFormat.format;

import com.postech.lending.creditanalysis.model.AnalysisCredit;
import com.postech.lending.creditanalysis.service.strategy.CalculationFactory;
import com.postech.lending.creditanalysis.service.strategy.CalculationStrategy;
import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class InterestRateCalculator {

    private static final BigDecimal ANNUAL_INTEREST_RATE = new BigDecimal("0.12");

    public BigDecimal amountToBePaidAfterPeriod(AnalysisCredit analysisCredit) {
        BigDecimal desiredValue = analysisCredit.getRequestedValue();
        int numberInstallment = analysisCredit.getNumberInstallment();
        BigDecimal monthlyIncome = calculateByMonthlyIncome(analysisCredit.getMonthlyIncome(), ANNUAL_INTEREST_RATE);

        log.info("Iniciando o cálculo do valor a ser pago para o cliente com documento: "  + analysisCredit.getDocument());

        BigDecimal addInterestRate = BigDecimal.ONE.add(monthlyIncome).pow(numberInstallment);

        BigDecimal finalValue = getFinalValue(numberInstallment, desiredValue.multiply(addInterestRate).setScale(2, RoundingMode.HALF_UP));

        log.info(format("Cálculo finalizado com sucesso. Valor final: %s{0}", finalValue));

        return finalValue;
    }

    public BigDecimal getFinalValue(int numberInstallment, BigDecimal desiredValue) {
        CalculationStrategy strategy = CalculationFactory.getCalculationStrategy(numberInstallment);
        BigDecimal value = strategy.calculate(desiredValue, numberInstallment);
        log.info("Cálculo para {} anos de juros aplicado.", numberInstallment / 12);
        return value;
    }

    public BigDecimal calculateTotalRate(AnalysisCredit analysisCredit) {
        BigDecimal desiredValue = analysisCredit.getRequestedValue();
        BigDecimal totalInterest = amountToBePaidAfterPeriod(analysisCredit);
        return totalInterest.subtract(desiredValue).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateTotalPercentage(AnalysisCredit analysisCredit) {
        BigDecimal totalInterest = amountToBePaidAfterPeriod(analysisCredit);
        return totalInterest.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateByMonthlyIncome(BigDecimal monthlyIncome, BigDecimal baseInterestRate) {
        BigDecimal interestRateAdjustment;

        if (monthlyIncome.compareTo(new BigDecimal("5000.00")) < 0) {
            interestRateAdjustment = new BigDecimal("0.005");
        } else if (monthlyIncome.compareTo(new BigDecimal("10000.00")) < 0) {
            interestRateAdjustment = new BigDecimal("0.0075");
        } else {
            interestRateAdjustment = new BigDecimal("0.01");
        }
        return baseInterestRate.add(interestRateAdjustment);
    }

}



