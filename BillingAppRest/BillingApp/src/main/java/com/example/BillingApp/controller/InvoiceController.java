package com.example.BillingApp.controller;


import com.example.BillingApp.dtos.invoices.CreateInvoiceDto;
import com.example.BillingApp.dtos.invoices.CreateInvoiceResponseDto;
import com.example.BillingApp.dtos.invoices.InvoiceResponseDto;
import com.example.BillingApp.dtos.invoices.InvoiceSetStatusResponseDto;
import com.example.BillingApp.services.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping("/invoices")
    private ResponseEntity<Map<String, Serializable>> createInvoice(@Valid @RequestBody CreateInvoiceDto createInvoiceDto) {
        try {
            CreateInvoiceResponseDto invoiceResponse = invoiceService.createInvoice(createInvoiceDto);
            return ResponseEntity.status(201).body(Map.of("invoiceId",invoiceResponse.invoiceId(),"status",invoiceResponse.status()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }


    }
    @GetMapping("/invoices/{id}")
    private ResponseEntity<Map<String, InvoiceResponseDto>> getInvoiceById(@PathVariable Long id) {

        try {
            InvoiceResponseDto invoiceResponse = invoiceService.getInvoiceById(id);
            return ResponseEntity.status(200).body(Map.of("invoices",invoiceResponse));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }

    }


    @PutMapping("/invoices/{id}/pay")
    private ResponseEntity<Map<String, String>> payInvoice(@PathVariable Long id) {
        try {
            InvoiceSetStatusResponseDto paidInvoice = invoiceService.payInvoice(id);
            return ResponseEntity.status(200).body(Map.of("status", paidInvoice.status()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/invoices/unpaid")
    private ResponseEntity<Map<String, Object>> getUnpaidInvoicesAfterFifteenDays() {
        try {
            var clients = invoiceService.checkUnpaidInvoicesAfterFifteenDays();
            return ResponseEntity.status(200).body(Map.of("clients", clients));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
   
    


}