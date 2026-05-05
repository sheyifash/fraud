package com.example.frauddetection.Dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Valid
public class LogInReq {
    @NotBlank (message = "username is requires")
    private String username;
    @NotBlank (message = "password is rwquird to login")
    private String password;
}
