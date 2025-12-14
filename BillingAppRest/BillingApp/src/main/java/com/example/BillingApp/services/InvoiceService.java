package com.example.BillingApp.services;

import com.example.BillingApp.domain.Client;
import com.example.BillingApp.domain.Invoice;
import com.example.BillingApp.dtos.clients.ClientResponseDto;
import com.example.BillingApp.dtos.invoices.CreateInvoiceDto;
import com.example.BillingApp.dtos.invoices.CreateInvoiceResponseDto;

import com.example.BillingApp.dtos.invoices.InvoiceResponseDto;
import com.example.BillingApp.dtos.invoices.InvoiceSetStatusResponseDto;
import com.example.BillingApp.enums.PaymentMethod;
import com.example.BillingApp.repository.InvoiceRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class InvoiceService {

    private final ClientService clientService;
    private final InvoiceRepository invoiceRepository;

    public InvoiceService(ClientService clientService, InvoiceRepository invoiceRepository) {
        this.clientService = clientService;
        this.invoiceRepository = invoiceRepository;
    }

    public CreateInvoiceResponseDto createInvoice(CreateInvoiceDto createInvoiceDto) {

        Invoice invoice = new Invoice();
        invoice.setAmount(createInvoiceDto.amount());
        invoice.setDescription(createInvoiceDto.description());

        Client client = clientService.findClientById(createInvoiceDto.customerId());
        invoice.setClient(client);
        invoice.setStatus("PENDING");
        invoice.setPaymentMethod(PaymentMethod.valueOf(createInvoiceDto.paymentMethod()));
        invoice.setDateEmission(LocalDate.now());

        Invoice saveInvoice = this.invoiceRepository.save(invoice);

        return new CreateInvoiceResponseDto(
                saveInvoice.getId(),
                saveInvoice.getStatus());

    }

    public InvoiceResponseDto getInvoiceById(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invoice not found"));

        return new InvoiceResponseDto(
                invoice.getId(),
                invoice.getAmount(),
                invoice.getDescription(),
                invoice.getDateEmission(),
                invoice.getDatePaiement(),
                invoice.getStatus(),
                invoice.getPaymentMethod()

        );
    }

    public InvoiceSetStatusResponseDto payInvoice(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invoice not found"));

        invoice.setStatus("PAID");
        invoice.setDatePaiement(LocalDate.now());

        Invoice updatedInvoice = invoiceRepository.save(invoice);

        return new InvoiceSetStatusResponseDto(
                updatedInvoice.getStatus());
    }

    public Set<ClientResponseDto> checkUnpaidInvoicesAfterFifteenDays() {
        List<Invoice> invoices = invoiceRepository.findByStatus("UNPAID");
        List<ClientResponseDto> notPaidclient = invoices.stream()
                .filter(invoice -> invoice.getDateEmission().plusDays(1).isBefore(LocalDate.now()))
                .map((invoice) -> new ClientResponseDto(
                        invoice.getClient().getId(),
                        invoice.getClient().getFirstName(),
                        invoice.getClient().getLastName(),
                        invoice.getClient().getEmail(),
                        invoice.getClient().getPhoneNumber(),
                        invoice.getClient().getAddress()))
                .toList();

        Set<ClientResponseDto> clients = new HashSet<>();
        clients.addAll(notPaidclient);

        return clients;

    }

}
