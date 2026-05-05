package com.example.frauddetection.Service;

import com.example.frauddetection.Dto.LogInReq;
import com.example.frauddetection.Dto.LogInResp;
import com.example.frauddetection.Dto.RegistrationReq;
import com.example.frauddetection.Dto.RegistrationResp;
import com.example.frauddetection.Entity.UserModel;
import com.example.frauddetection.Enum.AuthStatus;
import com.example.frauddetection.Enum.Role;
import com.example.frauddetection.Exception.UserAlreadyExists;
import com.example.frauddetection.Mapper.RegistrationMapper;
import com.example.frauddetection.Repo.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AuthService {
private final JwtService jwtService;
private final PasswordEncoder passwordEncoder;
private final UserRepo userRepo;

    public AuthService(JwtService jwtService, PasswordEncoder passwordEncoder, UserRepo userRepo) {
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
    }
        public RegistrationResp register(RegistrationReq req) {
            if (userRepo.existsByUsername(req.getUsername())) {
                throw new UserAlreadyExists("User already exists");
            }

            UserModel user = RegistrationMapper.mapToEntity(req);
            user.setStatus(AuthStatus.SUCCESSFUL);
            user.setMessage("User Successfully Created");
            String encodedPassword = passwordEncoder.encode(req.getPassword());
            user.setPassword(encodedPassword);
            user.setMerchantName(req.getMerchantName());
            if (req.getAdminId().isBlank()) {
                user.setRole(Role.ROLE_MERCHANT);
            }
            else {
                user.setRole(Role.ROLE_ADMIN);
            }
            String merchantId = UUID.randomUUID().toString().substring(0, 9);
            user.setMerchantID(merchantId);
            UserModel savedUser = userRepo.save(user);
            return RegistrationMapper.mapToDto(savedUser);
        }

        public LogInResp login(LogInReq req) {
            UserModel user = (UserModel) userRepo.findByUsername(req.getUsername())
                    .orElseThrow(() -> new RuntimeException("Invalid username or password"));
            if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
                throw new RuntimeException("Invalid username or password");
            }
            String token = jwtService.generateToken(user);
            LogInResp resp = new LogInResp();
            resp.setToken(token);
            resp.setStatus(AuthStatus.SUCCESSFUL);
            resp.setMessage("Login Successful");

            return resp;
        }
    }