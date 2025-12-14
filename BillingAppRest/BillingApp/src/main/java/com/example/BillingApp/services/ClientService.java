
package com.example.BillingApp.services;

import com.example.BillingApp.domain.Client;

import com.example.BillingApp.dtos.clients.ClientResponseDto;
import com.example.BillingApp.dtos.clients.CreateClientDto;
import com.example.BillingApp.dtos.invoices.InvoiceResponseDto;
import com.example.BillingApp.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;

    }

    public ClientResponseDto createClient(CreateClientDto createClientDto) {
        Client client = new Client();
        client.setFirstName(createClientDto.firstName());
        client.setLastName(createClientDto.lastName());
        client.setEmail(createClientDto.email());
        client.setAddress(createClientDto.address());
        client.setPhoneNumber(createClientDto.phoneNumber());
        clientRepository.save(client);
        return new ClientResponseDto(
                client.getId(),
                client.getFirstName(),
                client.getLastName(),
                client.getEmail(),
                client.getPhoneNumber(),
                client.getAddress());
    }

    public Client findClientById(Long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));
    }

    public List<InvoiceResponseDto> getClientInvoices(Long clientId) {

        Client client = this.findClientById(clientId);

        return client.getInvoices().stream().map(invoice -> new InvoiceResponseDto(
                invoice.getId(),
                invoice.getAmount(),
                invoice.getDescription(),
                invoice.getDateEmission(),
                invoice.getDatePaiement(),
                invoice.getStatus(),
                invoice.getPaymentMethod())).toList();

    }



    public BigDecimal getClientTotal(Long clientId) {
        Client client = this.findClientById(clientId);
        return client.getInvoices().stream()
                .filter(invoice -> invoice.getStatus().equals("PAID"))
                .map(invoice -> invoice.getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }
    
   
}
