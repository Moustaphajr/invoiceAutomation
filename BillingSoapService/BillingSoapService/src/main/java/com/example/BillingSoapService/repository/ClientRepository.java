package com.example.BillingSoapService.repository;

import com.example.BillingSoapService.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
