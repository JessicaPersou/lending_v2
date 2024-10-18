package com.postech.lending.creditanalysis.model;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InterestRateCalculation {

    private static final BigDecimal ANNUAL_INTEREST_RATE = new BigDecimal("0.12"); // 12% ao ano
    private static final BigDecimal MONTHLY_INTEREST_RATE = new BigDecimal("0.01"); // 1% ao mês
    private static final BigDecimal DAILY_INTEREST_RATE = new BigDecimal("0.0003"); // Aproximadamente 0.03% ao dia
    private static final int ONE_YEAR_IN_MONTHS = 12;
    private static final int TWO_YEARS_IN_MONTHS = 24;
    private static final int THREE_YEARS_IN_MONTHS = 36;
    private static final int FOUR_YEARS_IN_MONTHS = 48;

    private static final Logger LOGGER = Logger.getLogger(InterestRateCalculation.class.getName());


    // Método para calcular o montante a ser pago após um período
    public BigDecimal amountToBePaidAfterPeriod(AnalysisCredit analysisCredit) {
        try {
            BigDecimal desiredValue = analysisCredit.getRequestedValue();
            int numberInstallment = analysisCredit.getNumberInstallment();
            BigDecimal finalValue;

            // Log de início do cálculo
            LOGGER.info("Iniciando o cálculo do valor a ser pago para o cliente com documento: "
                    + analysisCredit.getDocument());

            // Cálculo básico de juros compostos mensais
            BigDecimal addInterestRate = BigDecimal.ONE.add(MONTHLY_INTEREST_RATE).pow(numberInstallment);
            finalValue = desiredValue.multiply(addInterestRate).setScale(2, RoundingMode.HALF_UP);

            // Log do cálculo básico
            LOGGER.info("Valor inicial com juros mensais calculado: " + finalValue);

            // Ajuste para períodos de 1 ano
            if (numberInstallment <= ONE_YEAR_IN_MONTHS) {
                finalValue = calculateWithAnnualRate(desiredValue, 1);
                LOGGER.info("Cálculo para 1 ano de juros aplicado.");
            }
            // Ajuste para 2 anos (24 meses)
            else if (numberInstallment <= TWO_YEARS_IN_MONTHS) {
                finalValue = calculateWithAnnualRate(desiredValue, 2);
                LOGGER.info("Cálculo para 2 anos de juros aplicado.");
            }
            // Ajuste para 3 anos (36 meses)
            else if (numberInstallment <= THREE_YEARS_IN_MONTHS) {
                finalValue = calculateWithAnnualRate(desiredValue, 3);
                LOGGER.info("Cálculo para 3 anos de juros aplicado.");
            }
            // Ajuste para 4 anos (48 meses)
            else if (numberInstallment <= FOUR_YEARS_IN_MONTHS) {
                finalValue = calculateWithAnnualRate(desiredValue, 4);
                LOGGER.info("Cálculo para 4 anos de juros aplicado.");
            }

            // Log de sucesso do cálculo final
            LOGGER.info("Cálculo finalizado com sucesso. Valor final: " + finalValue);

            return finalValue;

        } catch (RuntimeException e) {
            // Log de erro
            LOGGER.log(Level.SEVERE, "Erro ao calcular o valor de juros para o documento: %s".formatted(analysisCredit.getDocument()), e);
            throw new RuntimeException("Não foi possível calcular o valor de juros", e);
        }
    }

    // Método auxiliar para calcular com taxa de juros anual
    private BigDecimal calculateWithAnnualRate(BigDecimal desiredValue, int years) {
        BigDecimal annualInterest = ANNUAL_INTEREST_RATE.multiply(BigDecimal.valueOf(years));
        return desiredValue.multiply(BigDecimal.ONE.add(annualInterest)).setScale(2, RoundingMode.HALF_UP);
    }

    // Método para calcular o total de juros
    public BigDecimal calculateTotalRate(AnalysisCredit analysisCredit) {
        BigDecimal desiredValue = analysisCredit.getRequestedValue();
        BigDecimal totalInterest = amountToBePaidAfterPeriod(analysisCredit);
        return totalInterest.subtract(desiredValue).setScale(2, RoundingMode.HALF_UP);
    }
}



