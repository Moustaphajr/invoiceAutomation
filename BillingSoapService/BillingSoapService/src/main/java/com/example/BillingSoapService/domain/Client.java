package com.example.BillingSoapService.domain;


import com.example.BillingSoapService.domain.Invoice;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String address;

    @JsonManagedReference
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Invoice> invoices;





}
