package com.msp.controllers;

import com.msp.payloads.DTO.UserDTO;
import com.msp.payloads.requests.LoginRequest;
import com.msp.payloads.responses.AuthResponse;
import com.msp.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@Valid @RequestBody UserDTO userDTO) throws Exception {
        AuthResponse response = authService.signup(userDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @Valid @RequestBody LoginRequest req) throws Exception {
        AuthResponse response =
                authService.login(req.getEmail(), req.getPassword());
        return ResponseEntity.ok(response);
    }
}
