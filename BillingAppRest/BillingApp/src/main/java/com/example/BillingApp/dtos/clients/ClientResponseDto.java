package com.example.BillingApp.dtos.clients;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

public record ClientResponseDto(

      Long id,

        String firstName,

        String lastName,

        String email,


        String phoneNumber,


        String address
) {
}
