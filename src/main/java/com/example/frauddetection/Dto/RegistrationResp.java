package com.example.frauddetection.Dto;

import com.example.frauddetection.Enum.AuthStatus;
import com.example.frauddetection.Enum.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegistrationResp {
    private String merchantName;
    private String message;
    private AuthStatus status;
    private Role role;
    private String merchantId;
}
