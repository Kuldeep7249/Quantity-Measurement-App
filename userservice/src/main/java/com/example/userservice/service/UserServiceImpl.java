package com.example.userservice.service;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.entity.User;
import com.example.userservice.exception.InvalidCredentialsException;
import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {

        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("User already exists");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return userRepository.save(user);
    }

    public String loginUser(UserDTO user) {

        User u = userRepository.findByEmail(user.getEmail());

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