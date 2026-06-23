package com.msp.services;

import com.msp.payloads.DTO.UserDTO;

public interface AuthService {

    AuthResponse login(String email, String password);
    AuthResponse signup(UserDTO req);
}
