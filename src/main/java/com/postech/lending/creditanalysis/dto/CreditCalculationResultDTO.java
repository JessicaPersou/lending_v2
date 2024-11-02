package com.postech.lending.creditanalysis.dto;

import com.postech.lending.creditanalysis.model.CreditCalculationResult;
import com.postech.lending.creditanalysis.model.Installment;
import com.postech.lending.creditanalysis.model.enums.StatusAnalysisEnum;
import com.postech.lending.infrastructure.GeneratedBarCode;
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

    private Long id;
    private BigDecimal interestRate;
    private BigDecimal totalAmount;
    private int installmentNumber;
    private LocalDate calculationDate;
    private LocalDate analysisExpirationDate;
    private List<InstallmentDTO> installmentDTOList = new ArrayList<>();
    private BigDecimal totalInterestPercentage;
    private BigDecimal totalInterestPaid;
    private StatusAnalysisEnum analysisStatusDescription;
    private String documentClient;
    private String nameClient;

    public CreditCalculationResultDTO(CreditCalculationResult creditCalculationResult,
            List<Installment> installmentDTOList) {
        this.id = creditCalculationResult.getId();
        this.nameClient = creditCalculationResult.getNameClient();
        this.documentClient = creditCalculationResult.getDocumentClient();
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
            installmentDTO.setBarCode(GeneratedBarCode.generateBarCodeBlocks() + installment.getInstallmentAmount().toString().replace(".", ""));

            this.installmentDTOList.add(installmentDTO);
        });

        this.totalInterestPercentage = creditCalculationResult.getTotalInterestPercentage();
        this.totalInterestPaid = creditCalculationResult.getTotalInterestPaid();
        this.analysisStatusDescription = creditCalculationResult.getAnalysisStatusDescription();
    }

    public CreditCalculationResultDTO(CreditCalculationResult creditCalculationResult) {
        this.id = creditCalculationResult.getId();
        this.nameClient = creditCalculationResult.getNameClient();
        this.documentClient = creditCalculationResult.getDocumentClient();
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

        this.totalInterestPercentage = creditCalculationResult.getTotalInterestPercentage();
        this.totalInterestPaid = creditCalculationResult.getTotalInterestPaid();
        this.analysisStatusDescription = creditCalculationResult.getAnalysisStatusDescription();
    }

}
