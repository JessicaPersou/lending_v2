package com.postech.lending.creditanalysis.dto;

import com.postech.lending.creditanalysis.model.CreditCalculationResult;
import com.postech.lending.creditanalysis.model.enums.StatusAnalysisEnum;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditCalculationResultDTO {

    private Long id;  // Identificador único gerado automaticamente
    private BigDecimal interestRate;  // Taxa de juros calculada
    private BigDecimal totalAmount;  // Valor total a ser pago (valor + juros)
    private int installmentNumber;  // Valor de cada parcela após cálculo da taxa de juros
    private LocalDate calculationDate;  // Data em que o cálculo foi feito
    private LocalDate analysisExpirationDate;       // Data de vencimento
    private List<InstallmentDTO> installmentDTOList = new ArrayList<>(); //lista de parcelas e suas informações
    private BigDecimal totalInterestPercentage; // porcentagem de juros
    private BigDecimal totalInterestPaid; //total de juros a pagar
    private StatusAnalysisEnum analysisStatusDescription; //status da analise de credito
    private AnalysisCreditDTO analysisCreditId;               // Data de vencimento

    public CreditCalculationResultDTO(CreditCalculationResult creditCalculationResult) {
        this.id = creditCalculationResult.getId();
        this.interestRate = creditCalculationResult.getInterestRate();
        this.totalAmount = creditCalculationResult.getTotalAmount();
        this.installmentNumber = creditCalculationResult.getInstallmentNumber();
        this.calculationDate = creditCalculationResult.getCalculationDate();
        this.analysisExpirationDate = creditCalculationResult.getAnalysisExpirationDate();
        this.installmentDTOList = new ArrayList<>();
        installmentDTOList.forEach(installment -> {

            InstallmentDTO installmentDTO = new InstallmentDTO();
            installmentDTO.setId(installment.getId());
            installmentDTO.setInstallmentNumber(installment.getInstallmentNumber());
            installmentDTO.setInstallmentAmount(installment.getInstallmentAmount());
            installmentDTO.setDueDate(installment.getDueDate());

            this.installmentDTOList.add(installmentDTO);
        });

        this.totalInterestPercentage = creditCalculationResult.getTotalInterestPercentage(); // porcentagem de juros
        this.totalInterestPaid = creditCalculationResult.getTotalInterestPaid(); //total de juros a pagar
        this.analysisStatusDescription = creditCalculationResult.getAnalysisStatusDescription(); //status da analise de credito
        this.analysisCreditId = new AnalysisCreditDTO(creditCalculationResult.getAnalysisCreditId());
    }

}
