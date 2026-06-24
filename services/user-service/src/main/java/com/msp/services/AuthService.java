package com.msp.services;

import com.msp.payloads.DTO.UserDTO;
import com.msp.payloads.responses.AuthResponse;

public interface AuthService {

    AuthResponse login(String email, String password) throws Exception;
    AuthResponse signup(UserDTO req) throws Exception;
}
