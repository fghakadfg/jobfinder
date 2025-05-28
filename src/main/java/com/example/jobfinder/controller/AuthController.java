package com.example.jobfinder.controller;

import com.example.jobfinder.entity.User;
import com.example.jobfinder.security.JwtUtil;
import com.example.jobfinder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
            UserDetails userDetails = authentication.getPrincipal() instanceof UserDetails
                    ? (UserDetails) authentication.getPrincipal()
                    : userDetailsService.loadUserByUsername(loginRequest.getEmail());
            User user = userService.findByEmail(loginRequest.getEmail());
            String token = jwtUtil.generateToken(userDetails, user.getId()); // Используем новый метод с id
            String role = "ROLE_" + user.getRole().name();
            return ResponseEntity.ok(new AuthResponse(token, role));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Неверный email или пароль: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Внутренняя ошибка сервера: " + e.getMessage());
        }
    }
}

class LoginRequest {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

class AuthResponse {
    private String token;
    private String role;

    public AuthResponse(String token, String role) {
        this.token = token;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}