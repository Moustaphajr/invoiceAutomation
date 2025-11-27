package com.example.BillingApp.dtos.invoices;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateInvoiceDto(

    @NotNull
    @Positive
    Long customerId,

    @NotNull
    @Positive
    BigDecimal amount,
    @NotEmpty
    String description,

    @NotEmpty
    String paymentMethod
) {
}
