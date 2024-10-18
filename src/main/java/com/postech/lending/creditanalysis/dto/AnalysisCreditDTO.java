package com.postech.lending.creditanalysis.dto;

import com.postech.lending.creditanalysis.model.AnalysisCredit;
import com.postech.lending.creditanalysis.model.enums.StatusAnalysisEnum;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnalysisCreditDTO {
    private Long id;
    private BigDecimal requestedValue;       // Valor solicitado pelo cliente
    private BigDecimal monthlyIncome;        // Renda mensal do cliente
    private String document;                 // Documento do cliente (CPF ou CNPJ)
    private int numberInstallment;           // Quantidade de parcelas
    private StatusAnalysisEnum statusAnalysis;   // Status da análise de crédito

    public AnalysisCreditDTO(AnalysisCredit analysisCredit) {
        this.id = analysisCredit.getId();
        this.requestedValue = analysisCredit.getRequestedValue();
        this.monthlyIncome = analysisCredit.getMonthlyIncome();
        this.document = analysisCredit.getDocument();
        this.numberInstallment = analysisCredit.getNumberInstallment();
        this.statusAnalysis = analysisCredit.getStatusAnalysis();
    }
}
