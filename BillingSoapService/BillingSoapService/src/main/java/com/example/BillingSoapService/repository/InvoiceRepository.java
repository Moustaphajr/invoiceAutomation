package com.example.BillingSoapService.repository;

import com.example.BillingSoapService.domain.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
