package com.example.theproject.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OperationDto {
    
    @NotBlank(message = "Type is mandatory")
    private String type;

    @NotBlank(message = "Recipient is mandatory")
    private String recipientRib;

    @Min(value = 0, message = "the amount must be positive")
    private Double montant;

}




