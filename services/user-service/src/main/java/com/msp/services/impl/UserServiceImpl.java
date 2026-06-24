package com.msp.services.impl;

import com.msp.mappers.UserMapper;
import com.msp.models.User;
import com.msp.payloads.DTO.UserDTO;
import com.msp.repositories.UserRepository;
import com.msp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDTO getUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            throw new Exception("User not found with given email!");
        }

        return UserMapper.toDTO(user);
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User with given id does not exist!")
                );
        return UserMapper.toDTO(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
