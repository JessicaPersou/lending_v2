package com.postech.lending.contract.model;

import com.postech.lending.client.model.Client;
import com.postech.lending.creditanalysis.model.enums.StatusAnalysisEnum;
import jakarta.persistence.*;
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
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client clientId;

    @Column(name = "client_document")
    private String clientDocument;

    @Column(name = "analysis_amount", nullable = false)
    private double analysisAmount;

    @Column(name = "number_of_installments", nullable = false)
    private int numberOfInstallments;

    @Column(name = "installments_details", nullable = false)
    private double installmentDetails;

    @Column(name = "interest_rate", nullable = false)
    private double interestRate;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusAnalysisEnum status;


}
