package com.postech.lending.creditanalysis.model;

import com.postech.lending.creditanalysis.model.enums.StatusAnalysisEnum;
import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.*;
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
@Entity
@Table(name = "credit_calculation_result")
public class CreditCalculationResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Identificador único gerado automaticamente

    @Column(name = "interest_rate", nullable = false)
    private BigDecimal interestRate;  // Taxa de juros calculada

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;  // Valor total a ser pago (valor + juros)

    @Column(name = "installment_number", nullable = false)
    private int installmentNumber;  // quantidade de parcela  desejada pelo cliente

    @Column(name = "calculation_date", nullable = false)
    private LocalDate calculationDate;  // Data em que o cálculo foi feito

    @Column(name = "due_date", nullable = false)
    private LocalDate analysisExpirationDate;       // Data de vencimento

    @Column(name = "installment_list", nullable = false)
    @OneToMany(mappedBy = "creditCalculationResultId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Installment> installmentsList = new ArrayList<>(); //parcela com juros

    @Column(name = "total_interest_percentage", nullable = false)
    private BigDecimal totalInterestPercentage; // porcentagem de juros total

    @Column(name = "total_interest_paid", nullable = false)
    private BigDecimal totalInterestPaid; //total de juros a pagar

    @Column(name = "analysis_status_description", nullable = false)
    private StatusAnalysisEnum analysisStatusDescription; //status da analise de credito

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "analysis_credit_id", nullable = false)
    private AnalysisCredit analysisCreditId;


}
