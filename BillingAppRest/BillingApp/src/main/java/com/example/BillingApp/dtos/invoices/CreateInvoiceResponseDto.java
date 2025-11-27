package com.example.BillingApp.dtos.invoices;

public record CreateInvoiceResponseDto(
        Long invoiceId,
        String status
) {
}
