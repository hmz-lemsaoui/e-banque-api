package com.example.theproject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    @NotBlank(message = "id is required")
    private int id;

    @Email
    @NotEmpty(message = "email is required")
    private String email;
    @NotBlank(message = "password is required")
    private String password;

    @NotBlank(message = "username is required")
    private String fullName;

    @NotBlank(message = "rib is required")
    private String rib;

    // boolean
    @NotBlank(message = "isVerified is required")
    private boolean isActivated;


}
