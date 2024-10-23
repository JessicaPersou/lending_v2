package com.postech.lending.creditanalysis.dto;

import com.postech.lending.creditanalysis.model.Installment;
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
public class InstallmentDTO {

    private Long id;
    private int installmentNumber;
    private LocalDate dueDate;
    private BigDecimal installmentAmount;
    private String barCode;

    public InstallmentDTO(Installment installment) {
        this.id = installment.getId();
        this.installmentNumber = installment.getInstallmentNumber();
        this.dueDate = installment.getDueDate();
        this.installmentAmount = installment.getInstallmentAmount();
        this.barCode = installment.getBarCode();
    }

}
