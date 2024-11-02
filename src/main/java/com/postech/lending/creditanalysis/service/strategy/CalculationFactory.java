package com.postech.lending.creditanalysis.service.strategy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CalculationFactory {

    private static final int ONE_YEAR_IN_MONTHS = 12;
    private static final int TWO_YEARS_IN_MONTHS = 24;
    private static final int THREE_YEARS_IN_MONTHS = 36;
    private static final int FOUR_YEARS_IN_MONTHS = 48;

    private CalculationFactory() {}

    public static CalculationStrategy getCalculationStrategy(int numberInstallment) {
        if (numberInstallment <= ONE_YEAR_IN_MONTHS) {
            log.info("Usando estratégia de cálculo para 1 ano");
            return new OneYearCalculation();
        } else if (numberInstallment <= TWO_YEARS_IN_MONTHS) {
            log.info("Usando estratégia de cálculo para 2 anos");
            return new TwoYearCalculation();
        } else if (numberInstallment <= THREE_YEARS_IN_MONTHS) {
            log.info("Usando estratégia de cálculo para 3 anos");
            return new ThreeYearCalculation();
        } else if (numberInstallment <= FOUR_YEARS_IN_MONTHS) {
            log.info("Usando estratégia de cálculo para 4 anos");
            return new FourYearCalculation();
        }

        log.warn("Número de parcelas não suportado: {}", numberInstallment);
        throw new IllegalArgumentException(
                "Número de parcelas não suportado, verifique o valor informado e tente novamente");
    }
}
