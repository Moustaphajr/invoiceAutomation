package com.example.BillingSoapService.service;

import billing.entities.*;


import com.example.BillingSoapService.domain.Client;
import com.example.BillingSoapService.domain.Invoice;

import com.example.BillingSoapService.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final ClientService clientService;

    public InvoiceService(InvoiceRepository invoiceRepository,ClientService clientService) {
        this.invoiceRepository = invoiceRepository;
        this.clientService = clientService;
    }


    public CreateInvoiceResponse createInvoice(CreateInvoiceRequest createInvoiceRequest){
        Client client=this.clientService.getClientById(createInvoiceRequest.getClientId());
        Invoice newInvoice=new Invoice();
        newInvoice.setClient(client);
        newInvoice.setDescription(createInvoiceRequest.getDescription());
        newInvoice.setAmount(createInvoiceRequest.getAmount());
        newInvoice.setStatus("PENDING");
        newInvoice.setDateEmission(LocalDate.now());
        Invoice savedInvoice=this.invoiceRepository.save(newInvoice);

        CreateInvoiceResponse createInvoiceResponse=new CreateInvoiceResponse();
        createInvoiceResponse.setInvoiceId(savedInvoice.getId());
        createInvoiceResponse.setStatus(savedInvoice.getStatus());

        return createInvoiceResponse;


    }


    public GetInvoiceByIdResponse getInvoiceByid(GetInvoiceByIdRequest getInvoiceByIdRequest){
        Invoice invoice=this.invoiceRepository.findById(getInvoiceByIdRequest.getId()).orElse(null);
        GetInvoiceByIdResponse response=new GetInvoiceByIdResponse();
        InvoiceType invoiceType=new InvoiceType();
        invoiceType.setAmount(invoice.getAmount());
        invoiceType.setClientId(invoice.getClient().getId());
        invoiceType.setDateEmission(invoice.getDateEmission());
        invoiceType.setDatePaiement(invoice.getDatePaiement());
        invoiceType.setDescription(invoice.getDescription());
        invoiceType.setId(invoice.getId());
        invoiceType.setStatus(InvoiceStatus.valueOf(invoice.getStatus()));
        invoiceType.setPaymentMethod(PaymentMethod.valueOf(String.valueOf(invoice.getPaymentMethod())));

        if(invoice!=null){
            response.setInvoice(invoiceType);
        }
        return response;
    }
}
