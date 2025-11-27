package com.example.BillingApp.controller;


import com.example.BillingApp.dtos.clients.ClientResponseDto;
import com.example.BillingApp.dtos.clients.CreateClientDto;
import com.example.BillingApp.dtos.invoices.InvoiceResponseDto;
import com.example.BillingApp.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/clients")
    public ResponseEntity<Map<String, ClientResponseDto>> createClient(
            @Valid @RequestBody CreateClientDto createClientDto) {
        {
            try {
                ClientResponseDto clientResponseDto = clientService.createClient(createClientDto);
                return ResponseEntity.status(201).body(Map.of("clients", clientResponseDto));
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().build();
            }
        }

    }

    @GetMapping("/clients/{clientId}/invoices")
    public ResponseEntity<Map<String, List<InvoiceResponseDto>>> getClientInvoices(@PathVariable Long clientId) {
        try {
            List<InvoiceResponseDto> invoices = clientService.getClientInvoices(clientId);
            return ResponseEntity.status(200).body(Map.of("invoices", invoices));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/clients/{clientId}/total")
    public ResponseEntity<Map<String, BigDecimal>> getClientTotal(@PathVariable Long clientId) {
        try {
          BigDecimal total = clientService.getClientTotal(clientId);
            return ResponseEntity.status(200).body(Map.of("total", total));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

}