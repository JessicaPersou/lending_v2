package com.postech.lending.creditanalysis.model;

import com.postech.lending.client.model.Client;
import com.postech.lending.creditanalysis.model.enums.StatusAnalysisEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.List;
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
    private Long id;

    @Column(name = "requested_value", nullable = false)
    private BigDecimal requestedValue;

    @Column(name = "monthly_income", nullable = false)
    private BigDecimal monthlyIncome;

    @Column(name = "document", nullable = false, length = 20)
    private String document;

    @Column(name = "number_installment", nullable = false)
    private int numberInstallment;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_analysis", nullable = false)
    private StatusAnalysisEnum statusAnalysis;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client clientId;

    @OneToOne(mappedBy = "analysisCreditId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CreditCalculationResult creditCalculationResultId;

    @OneToMany(mappedBy = "analysisCreditId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Installment> installments;


}
