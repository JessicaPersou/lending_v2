package com.postech.lending.creditanalysis.service.calculator;

import com.postech.lending.creditanalysis.dto.InstallmentDTO;
import com.postech.lending.creditanalysis.model.CreditCalculationResult;
import com.postech.lending.creditanalysis.model.Installment;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InstallmentsCalculator {


    public List<Installment> calculateInstallments(CreditCalculationResult creditCalculationResult) {
        //passar o valor com total com juros e dividir as parcelas
        BigDecimal totalValueToPaid = creditCalculationResult.getTotalAmount();
        int installmentNumber = creditCalculationResult.getInstallmentNumber();
        BigDecimal installmentAmount = totalValueToPaid.divide(BigDecimal.valueOf(installmentNumber),2, RoundingMode.HALF_UP);

//        fazer um for para adicionar as informações de cada parcela

        List<Installment> installments = new ArrayList<>();

        for (int i = 1; i <= installmentNumber; i++) {
            Installment installment = new Installment();
            installment.setInstallmentNumber(i); // Define o número da parcela
            installment.setInstallmentAmount(installmentAmount); // Define o valor da parcela
            installment.setDueDate(LocalDate.now().plusMonths(i)); // Define a data de vencimento para cada parcela
            installments.add(installment); // Adiciona a parcela à lista
        }

        System.out.println("Installments generated: " + installments.size());

        return installments;
    }

}
