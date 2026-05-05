package com.example.frauddetection.Dto;

import com.example.frauddetection.Enum.Role;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Valid
public class RegistrationReq {
    @NotBlank (message = "first name is required")
    private String firstName;
    @NotBlank (message = "last name is required")
    private String lastName;
    @NotBlank (message = "email is required")
    private String username;
    @NotBlank (message = "merchantname is required")
    private String merchantName;
    @NotBlank (message = "password is required")
    private String password;
    @NotBlank (message = "mobile number is required")
    private String mobile;
    private String adminId;//optional
    private Role role;
}
