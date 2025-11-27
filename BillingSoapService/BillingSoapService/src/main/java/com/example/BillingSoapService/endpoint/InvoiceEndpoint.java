package com.example.BillingSoapService.endpoint;


import billing.entities.CreateInvoiceRequest;
import billing.entities.CreateInvoiceResponse;
import billing.entities.GetInvoiceByIdRequest;
import billing.entities.GetInvoiceByIdResponse;
import com.example.BillingSoapService.service.InvoiceService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;

import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class InvoiceEndpoint {

    private final String  NameSpaceURI = "http://billingSoapApp.com/billing";
    private final InvoiceService invoiceService;

    public InvoiceEndpoint(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }


   @PayloadRoot(namespace = NameSpaceURI, localPart = "CreateInvoiceRequest")
   @ResponsePayload
    public CreateInvoiceResponse createInvoice(@RequestPayload CreateInvoiceRequest createInvoiceRequest) {
        try {
            return invoiceService.createInvoice(createInvoiceRequest);
        } catch (Exception e) {
            System.err.println("Error creating invoice: " + e.getMessage());
           return null;
        }

    }
    @PayloadRoot(namespace = NameSpaceURI, localPart = "GetInvoiceByIdRequest")
    @ResponsePayload
    public GetInvoiceByIdResponse getInvoiceByIdResponse(@RequestPayload GetInvoiceByIdRequest getInvoiceByIdRequest) {
        try {
            return invoiceService.getInvoiceByid(getInvoiceByIdRequest);
        } catch (Exception e) {
            System.err.println("Error creating invoice: " + e.getMessage());
            return null;
        }

    }

}
