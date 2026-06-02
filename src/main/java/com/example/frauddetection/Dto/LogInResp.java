package com.example.frauddetection.Dto;

import com.example.frauddetection.Enum.AuthStatus;
import jakarta.validation.Valid;
import lombok.Data;

@Data
@Valid
public class LogInResp {
    private String message;
    private AuthStatus status;
    private String token;
}
