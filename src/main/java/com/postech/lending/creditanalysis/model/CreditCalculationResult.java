package com.postech.lending.creditanalysis.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.*;
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

    @Column(name = "installment_amount", nullable = false)
    private BigDecimal installmentAmount;  // Valor de cada parcela após cálculo da taxa de juros

    @Column(name = "calculation_date", nullable = false)
    private LocalDate calculationDate;  // Data em que o cálculo foi feito

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;       // Data de vencimento

}
