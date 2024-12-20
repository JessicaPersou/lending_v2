package com.postech.lending.creditanalysis.service.util;

import com.postech.lending.creditanalysis.model.CreditCalculationResult;
import com.postech.lending.creditanalysis.model.Installment;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InstallmentsCalculator {

    public List<Installment> calculateInstallments(CreditCalculationResult creditCalculationResult) {
        BigDecimal totalValueToPaid = creditCalculationResult.getTotalAmount();
        int installmentNumber = creditCalculationResult.getInstallmentNumber();
        BigDecimal installmentAmount = totalValueToPaid.divide(BigDecimal.valueOf(installmentNumber), 2,
                RoundingMode.HALF_UP);

        List<Installment> installments = new ArrayList<>();

        for (int i = 1; i <= installmentNumber; i++) {
            Installment installment = new Installment();
            installment.setInstallmentNumber(i);
            installment.setInstallmentAmount(installmentAmount);
            installment.setDueDate(LocalDate.now().plusMonths(i));
            installments.add(installment);
        }

        log.info("Quantidade de Parcelamento Gerada: " + installments.size());

        return installments;
    }

}
