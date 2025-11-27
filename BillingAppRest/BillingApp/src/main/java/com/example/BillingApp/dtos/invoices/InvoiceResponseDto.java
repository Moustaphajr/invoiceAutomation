package com.example.BillingApp.dtos.invoices;


import com.example.BillingApp.enums.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDate;

public record InvoiceResponseDto(
        Long id,
        BigDecimal amount,
        String description,
        LocalDate dateEmission,
        LocalDate datePaiement,
        String status,
        PaymentMethod paymentMethod
) {
}
