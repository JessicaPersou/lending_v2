package com.postech.lending.contract.dto;

import com.postech.lending.creditanalysis.dto.CreditCalculationResultDTO;
import com.postech.lending.creditanalysis.model.CreditCalculationResult;
import com.postech.lending.creditanalysis.model.enums.StatusAnalysisEnum;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContractDTO {

    private String clientFullName;
    private String clientDocument;
    private double analysisAmount;
    private int numberOfInstallments;
    private double installmentDetails;
    private double interestRate;
    private LocalDate startDate;
    private LocalDate endDate;
    private StatusAnalysisEnum status;

    public ContractDTO(CreditCalculationResult calculationResult) {
        this.clientDocument = calculationResult.getDocumentClient();
        this.clientFullName = calculationResult.getNameClient();
        this.interestRate = calculationResult.getInterestRate().doubleValue();
        this.analysisAmount = calculationResult.getTotalAmount().doubleValue();
        this.startDate = calculationResult.getCalculationDate();
        this.installmentDetails = calculationResult.getInstallmentsList().indexOf(1);
        this.numberOfInstallments = calculationResult.getInstallmentNumber();
        this.endDate = calculationResult.getCalculationDate().plusDays(7);
        this.status = StatusAnalysisEnum.APPROVED;
    }
}