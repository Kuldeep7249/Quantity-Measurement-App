package com.example.userservice.Configuration;


import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.JWTService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();

        String email = oauthUser.getAttribute("email");
        String name  = oauthUser.getAttribute("name");

        // Auto-register user if first time login with Google
        User user = userRepository.findByEmail(email);
        if (user == null) {
            user = new User(name,email,"password");
            userRepository.save(user);
        }

        // Generate JWT for the OAuth user
        String token = jwtService.generateToken(email);
        Cookie cookie=new Cookie("accessToken",token);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        // Redirect to frontend with token in URL
        // Frontend reads it and stores in localStorage
        response.sendRedirect("http://localhost:5173/oauth-success?token=" + token);
    }
}