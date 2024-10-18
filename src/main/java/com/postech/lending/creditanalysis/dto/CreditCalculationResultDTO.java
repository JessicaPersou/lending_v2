package com.postech.lending.creditanalysis.dto;

import com.postech.lending.creditanalysis.model.CreditCalculationResult;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditCalculationResultDTO {
    private Long id;
    private BigDecimal interestRate;         // Taxa de juros calculada
    private BigDecimal totalAmount;          // Valor total a ser pago (valor + juros)
    private BigDecimal installmentAmount;    // Valor de cada parcela após cálculo da taxa de juros
    private LocalDate calculationDate;       // Data em que o cálculo foi feito
    private LocalDate dueDate;               // Data de vencimento

    public CreditCalculationResultDTO(CreditCalculationResult creditCalculationResult){
        this.id = creditCalculationResult.getId();
        this.interestRate = creditCalculationResult.getInterestRate();
        this.totalAmount = creditCalculationResult.getTotalAmount();
        this.installmentAmount = creditCalculationResult.getInstallmentAmount();
        this.calculationDate = creditCalculationResult.getCalculationDate();
        this.dueDate = creditCalculationResult.getDueDate();
    }

}
