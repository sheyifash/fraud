package com.example.frauddetection.Mapper;

import com.example.frauddetection.Dto.RegistrationReq;
import com.example.frauddetection.Dto.RegistrationResp;
import com.example.frauddetection.Entity.UserModel;
import org.springframework.stereotype.Component;

@Component
public class RegistrationMapper {
    public static RegistrationResp mapToDto(UserModel user){
        return RegistrationResp.builder()
                .status(user.getStatus())
                .message(user.getMessage())
                .role(user.getRole())
                .merchantName(user.getMerchantName())
                .merchantId(user.getMerchantID())
                .build();
    }
    public static UserModel mapToEntity(RegistrationReq request){
        UserModel user = new UserModel();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setAdminId(request.getAdminId());
        user.setMerchantName(request.getMerchantName());
        user.setRole(request.getRole());
        user.setMobile(request.getMobile());
        user.setPassword(request.getPassword());
        return user;
    }
}
