package com.quantitymeasurement.service;

import com.quantitymeasurement.dto.UserDTO;
import com.quantitymeasurement.entity.UserEntity;
import com.quantitymeasurement.exception.InvalidCredentialsException;
import com.quantitymeasurement.exception.UserNotFoundException;
import com.quantitymeasurement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTService jwtService;

    public UserEntity createUser(UserEntity user) {

        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("User already exists");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }

    public String loginUser(UserDTO user) {

        UserEntity u = userRepository.findByEmail(user.getEmail());

        if (u == null) {
            throw new UserNotFoundException("User not found");
        }

        if (!passwordEncoder.matches(user.getPassword(), u.getPassword())) {
            throw new InvalidCredentialsException("Invalid password");
        }

        return jwtService.generateToken(u.getEmail());
    }

    public void deleteUser(Long id) {

        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found");
        }

        userRepository.deleteById(id);
    }
}