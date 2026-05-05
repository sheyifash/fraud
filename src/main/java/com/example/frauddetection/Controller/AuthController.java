package com.example.frauddetection.Controller;

import com.example.frauddetection.Dto.*;
import com.example.frauddetection.Service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/register")
    public ResponseEntity<RegistrationResp> register(@RequestBody RegistrationReq req){
        RegistrationResp resp = authService.register(req);
        return  ResponseEntity.ok(resp);
    }
    @PostMapping("/logIn")
    public ResponseEntity<LogInResp> logIn(@RequestBody LogInReq req){
        LogInResp resp = authService.login(req);
        return ResponseEntity.ok(resp);
    }
}

