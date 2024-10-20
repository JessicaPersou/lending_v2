package com.postech.lending.creditanalysis.model;

import com.postech.lending.creditanalysis.dto.InstallmentDTO;
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
@Table(name = "installments")
public class Installment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "installment_number", nullable = false)
    private int installmentNumber; // Número da parcela (1, 2, 3...)

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate; // Data de vencimento da parcela

    @Column(name = "installment_amount", nullable = false)
    private BigDecimal installmentAmount; // Valor da parcela

    // Relacionamento com a análise de crédito (muitas parcelas para uma análise)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "analysis_credit_id")
    private AnalysisCredit analysisCreditId;

//     Outros atributos que podem ser adicionados futuramente:
     @Column(name = "late_fee")
     private BigDecimal lateFee; // Multa por atraso

    // Mapeamento para a entidade CreditCalculationResult (lado dono da relação)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credit_calculation_result_id") // Nome da coluna no banco de dados
    private CreditCalculationResult creditCalculationResultId;

    // @Column(name = "payment_status")
    // private String paymentStatus; // Status de pagamento da parcela (Paga, Atrasada, etc.)

    public InstallmentDTO convertToDTO() {
        InstallmentDTO dto = new InstallmentDTO();
        dto.setInstallmentNumber(this.getInstallmentNumber());
        dto.setInstallmentAmount(this.getInstallmentAmount());
        dto.setDueDate(this.getDueDate());
        return dto;
    }
}
