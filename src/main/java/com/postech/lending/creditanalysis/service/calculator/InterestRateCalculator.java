package com.postech.lending.creditanalysis.service.calculator;

import static java.text.MessageFormat.format;

import com.postech.lending.creditanalysis.model.AnalysisCredit;
import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class InterestRateCalculator {

    private static final BigDecimal ANNUAL_INTEREST_RATE = new BigDecimal("0.12"); // 12% ao ano
    private static final BigDecimal MONTHLY_INTEREST_RATE = new BigDecimal("0.01"); // 1% ao mês
    private static final BigDecimal DAILY_INTEREST_RATE = new BigDecimal("0.0003"); // Aproximadamente 0.03% ao dia
    private static final int ONE_YEAR_IN_MONTHS = 12;
    private static final int TWO_YEARS_IN_MONTHS = 24;
    private static final int THREE_YEARS_IN_MONTHS = 36;
    private static final int FOUR_YEARS_IN_MONTHS = 48;

    public BigDecimal amountToBePaidAfterPeriod(AnalysisCredit analysisCredit) {
        BigDecimal desiredValue = analysisCredit.getRequestedValue();
        int numberInstallment = analysisCredit.getNumberInstallment();
        BigDecimal monthlyIncome = calculateByMonthlyIncome(analysisCredit.getMonthlyIncome(), ANNUAL_INTEREST_RATE);


        BigDecimal finalValue;

        log.info("Iniciando o cálculo do valor a ser pago para o cliente com documento: "
                + analysisCredit.getDocument());
        BigDecimal addInterestRate = BigDecimal.ONE.add(monthlyIncome).pow(numberInstallment);
        finalValue = desiredValue.multiply(addInterestRate).setScale(2, RoundingMode.HALF_UP);

        finalValue = getFinalValue(numberInstallment, finalValue, desiredValue);

        log.info(format("Cálculo finalizado com sucesso. Valor final: %s{0}", finalValue));

        return finalValue;
    }

    private BigDecimal getFinalValue(int numberInstallment, BigDecimal finalValue, BigDecimal desiredValue) {
        if (numberInstallment <= ONE_YEAR_IN_MONTHS) {
            finalValue = calculateWithAnnualRate(desiredValue, 1);
            log.info("Cálculo para 1 ano de juros aplicado.");
        }else if (numberInstallment <= TWO_YEARS_IN_MONTHS) {
            finalValue = calculateWithAnnualRate(desiredValue, 2);
            log.info("Cálculo para 2 anos de juros aplicado.");
        } else if (numberInstallment <= THREE_YEARS_IN_MONTHS) {
            finalValue = calculateWithAnnualRate(desiredValue, 3);
            log.info("Cálculo para 3 anos de juros aplicado.");
        } else if (numberInstallment <= FOUR_YEARS_IN_MONTHS) {
            finalValue = calculateWithAnnualRate(desiredValue, 4);
            log.info("Cálculo para 4 anos de juros aplicado.");
        }
        return finalValue;
    }

    private BigDecimal calculateWithAnnualRate(BigDecimal desiredValue, int years) {
        BigDecimal annualInterest = ANNUAL_INTEREST_RATE.multiply(BigDecimal.valueOf(years));
        return desiredValue.multiply(BigDecimal.ONE.add(annualInterest)).setScale(2, RoundingMode.HALF_UP);
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



