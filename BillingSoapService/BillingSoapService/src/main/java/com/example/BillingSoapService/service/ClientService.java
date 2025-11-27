package com.example.BillingSoapService.service;


import com.example.BillingSoapService.domain.Client;
import com.example.BillingSoapService.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client getClientById(Long clientId){

        return clientRepository.findById(clientId).orElse(null);

    }
}
