package com.postech.lending.creditanalysis.model;

import com.postech.lending.creditanalysis.model.enums.StatusAnalysisEnum;
import java.math.BigDecimal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "analysis_credit")
public class AnalysisCredit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Identificador único gerado automaticamente

    @Column(name = "requested_value", nullable = false)
    private BigDecimal requestedValue;  // Valor solicitado pelo cliente

    @Column(name = "monthly_income", nullable = false)
    private BigDecimal monthlyIncome;  // Renda mensal do cliente

    @Column(name = "document", nullable = false, length = 20)
    private String document;  // Documento do cliente (CPF ou CNPJ)

    @Column(name = "number_installment", nullable = false)
    private int numberInstallment;  // Quantidade de parcelas

    @Enumerated(EnumType.STRING)
    @Column(name = "status_analysis", nullable = false)
    private StatusAnalysisEnum statusAnalysis;  // Status da análise de crédito


}
