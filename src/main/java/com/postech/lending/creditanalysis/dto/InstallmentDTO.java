package com.postech.lending.creditanalysis.dto;

import com.postech.lending.creditanalysis.model.AnalysisCredit;
import com.postech.lending.creditanalysis.model.Installment;
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
public class InstallmentDTO {

    private Long id;
    private int installmentNumber; // Número da parcela (1, 2, 3...)
    private LocalDate dueDate; // Data de vencimento da parcela
    private BigDecimal installmentAmount; // Valor da parcela
    private AnalysisCreditDTO analysisCreditId;

    // Outros atributos que podem ser adicionados futuramente:
    // @Column(name = "late_fee")
    // private BigDecimal lateFee; // Multa por atraso

    // @Column(name = "payment_status")
    // private String paymentStatus; // Status de pagamento da parcela (Paga, Atrasada, etc.)

    public InstallmentDTO(Installment installment) {
        this.id = installment.getId();
        this.installmentNumber = installment.getInstallmentNumber();
        this.dueDate = installment.getDueDate();
        this.installmentAmount = installment.getInstallmentAmount();
        this.analysisCreditId = new AnalysisCreditDTO(installment.getAnalysisCreditId());
    }

}
