package com.postech.lending.creditanalysis.dto;

import com.postech.lending.client.dto.ClientDTO;
import com.postech.lending.creditanalysis.model.AnalysisCredit;
import com.postech.lending.creditanalysis.model.enums.StatusAnalysisEnum;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnalysisCreditDTO {
    private Long id;
    private BigDecimal requestedValue;
    private BigDecimal monthlyIncome;
    private String document;
    private int numberInstallment;
    private StatusAnalysisEnum statusAnalysis;
    private ClientDTO clientId;
    private CreditCalculationResultDTO creditCalculationResultId;
    private List<InstallmentDTO> installments;

    public AnalysisCreditDTO(AnalysisCredit analysisCredit) {
        this.id = analysisCredit.getId();
        this.requestedValue = analysisCredit.getRequestedValue();
        this.monthlyIncome = analysisCredit.getMonthlyIncome();
        this.document = analysisCredit.getDocument();
        this.numberInstallment = analysisCredit.getNumberInstallment();
        this.statusAnalysis = analysisCredit.getStatusAnalysis();
        this.clientId = new ClientDTO(analysisCredit.getClientId());
        this.creditCalculationResultId= new CreditCalculationResultDTO(analysisCredit.getCreditCalculationResultId());
        this.installments = new ArrayList<>();

        installments.forEach(installment -> {
            InstallmentDTO installmentDTO = new InstallmentDTO();
            installmentDTO.setId(installment.getId());
            installmentDTO.setInstallmentNumber(installment.getInstallmentNumber());
            installmentDTO.setInstallmentAmount(installment.getInstallmentAmount());
            installmentDTO.setDueDate(installment.getDueDate());

            this.installments.add(installmentDTO);
        });
    }
}
