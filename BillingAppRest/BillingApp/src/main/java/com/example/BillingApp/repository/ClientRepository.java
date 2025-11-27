package com.example.BillingApp.repository;


import com.example.BillingApp.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
