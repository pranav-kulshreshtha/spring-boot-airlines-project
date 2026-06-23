package com.msp.payloads.responses;

import com.msp.payloads.DTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private String jwt;
    private String message;
    private String title;
    private UserDTO user;
}
