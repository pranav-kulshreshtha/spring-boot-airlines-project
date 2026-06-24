package com.msp.services;

import com.msp.models.User;
import com.msp.payloads.DTO.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO getUserByEmail(String email) throws Exception;
    UserDTO getUserById(Long id);
    List<User> getAllUsers();
}
