package com.postech.lending.creditanalysis.service.calculator;

import com.postech.lending.creditanalysis.model.AnalysisCredit;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InterestRateCalculator {

    private static final BigDecimal ANNUAL_INTEREST_RATE = new BigDecimal("0.12"); // 12% ao ano
    private static final BigDecimal MONTHLY_INTEREST_RATE = new BigDecimal("0.01"); // 1% ao mês
    private static final BigDecimal DAILY_INTEREST_RATE = new BigDecimal("0.0003"); // Aproximadamente 0.03% ao dia
    private static final int ONE_YEAR_IN_MONTHS = 12;
    private static final int TWO_YEARS_IN_MONTHS = 24;
    private static final int THREE_YEARS_IN_MONTHS = 36;
    private static final int FOUR_YEARS_IN_MONTHS = 48;

    private static final Logger LOGGER = Logger.getLogger(InterestRateCalculator.class.getName());

    public BigDecimal amountToBePaidAfterPeriod(AnalysisCredit analysisCredit) throws RuntimeException {
        BigDecimal desiredValue = analysisCredit.getRequestedValue();
        int numberInstallment = analysisCredit.getNumberInstallment();
        BigDecimal finalValue;

        LOGGER.info("Iniciando o cálculo do valor a ser pago para o cliente com documento: "
                + analysisCredit.getDocument());

        BigDecimal addInterestRate = BigDecimal.ONE.add(MONTHLY_INTEREST_RATE).pow(numberInstallment);
        finalValue = desiredValue.multiply(addInterestRate).setScale(2, RoundingMode.HALF_UP);

        LOGGER.info("Valor inicial com juros mensais calculado: %s".formatted(finalValue));

        if (numberInstallment <= ONE_YEAR_IN_MONTHS) {
            finalValue = calculateWithAnnualRate(desiredValue, 1);
            LOGGER.info("Cálculo para 1 ano de juros aplicado.");
            LOGGER.info("Cálculo finalizado com sucesso. Valor final: %s".formatted(finalValue));
        } else {
            if (numberInstallment <= TWO_YEARS_IN_MONTHS) {
                finalValue = calculateWithAnnualRate(desiredValue, 2);
                LOGGER.info("Cálculo para 2 anos de juros aplicado.");
            } else if (numberInstallment <= THREE_YEARS_IN_MONTHS) {
                finalValue = calculateWithAnnualRate(desiredValue, 3);
                LOGGER.info("Cálculo para 3 anos de juros aplicado.");
            } else if (numberInstallment <= FOUR_YEARS_IN_MONTHS) {
                finalValue = calculateWithAnnualRate(desiredValue, 4);
                LOGGER.info("Cálculo para 4 anos de juros aplicado.");
            }
            LOGGER.info("Cálculo finalizado com sucesso. Valor final: %s".formatted(finalValue));
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
}



