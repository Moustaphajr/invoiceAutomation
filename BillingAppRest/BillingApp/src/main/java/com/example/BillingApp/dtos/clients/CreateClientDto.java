package com.example.BillingApp.dtos.clients;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

public record CreateClientDto(

        @NotEmpty(message="Le prénom ne peut pas être vide")
        String firstName,
        @NotEmpty(message="Le nom de famille ne peut pas être vide")
        String lastName,
        @NotEmpty(message="L'email ne peut pas être vide")
         @Email(message="L'email doit être valide")
        String email,

           @NotEmpty(message="Le numéro de téléphone ne peut pas être vide")
                   @NumberFormat(
                       pattern = "\\+?[0-9. ()-]{7,25}"

                   )

           String phoneNumber,

        @NotEmpty(message="L'adresse ne peut pas être vide")
        String address



) {
}
