package com.postech.lending.creditanalysis.model;

import com.postech.lending.creditanalysis.model.enums.StatusAnalysisEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "credit_calculation_result")
public class CreditCalculationResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "interest_rate", nullable = false)
    private BigDecimal interestRate;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "installment_number", nullable = false)
    private int installmentNumber;

    @Column(name = "calculation_date", nullable = false)
    private LocalDate calculationDate;

    @Column(name = "due_date", nullable = false)
    private LocalDate analysisExpirationDate;

    @Column(name = "installment_list", nullable = false)
    @OneToMany(mappedBy = "creditCalculationResultId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Installment> installmentsList = new ArrayList<>();

    @Column(name = "total_interest_percentage", nullable = false)
    private BigDecimal totalInterestPercentage;

    @Column(name = "total_interest_paid", nullable = false)
    private BigDecimal totalInterestPaid;

    @Column(name = "analysis_status_description", nullable = false)
    private StatusAnalysisEnum analysisStatusDescription;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "analysis_credit_id", nullable = false)
    private AnalysisCredit analysisCreditId;

    @Column(name = "document_client")
    private String documentClient;

    @Column(name = "name_client")
    private String nameClient;


}
