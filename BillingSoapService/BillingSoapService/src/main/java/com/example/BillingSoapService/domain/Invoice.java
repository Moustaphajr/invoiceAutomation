package com.example.BillingSoapService.domain;


import com.example.BillingSoapService.enums.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "invoices")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "clientId", nullable = false, referencedColumnName = "id")
    private Client client;
    private BigDecimal amount;
    private String description;
    private LocalDate dateEmission;
    private LocalDate datePaiement;
    private String status;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

}
