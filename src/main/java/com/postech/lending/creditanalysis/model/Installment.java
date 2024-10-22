package com.postech.lending.creditanalysis.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "installments")
public class Installment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "installment_number", nullable = false)
    private int installmentNumber;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name = "installment_amount", nullable = false)
    private BigDecimal installmentAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "analysis_credit_id")
    private AnalysisCredit analysisCreditId;

    @Column(name = "late_fee")
    private BigDecimal lateFee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credit_calculation_result_id")
    private CreditCalculationResult creditCalculationResultId;

    @Column(name = "bar_code")
    private String barCode;


}
