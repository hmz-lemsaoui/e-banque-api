package com.example.theproject.controllers;

import com.example.theproject.config.TokenGenerator;
import com.example.theproject.dto.AuthresponseDto;
import com.example.theproject.dto.CustomErrorResponse;
import com.example.theproject.dto.LoginDto;
import com.example.theproject.dto.RegisterDto;
import com.example.theproject.models.Role;
import com.example.theproject.models.UserEntity;
import com.example.theproject.repository.RoleRepo;
import com.example.theproject.repository.UserRepo;
import com.example.theproject.services.AuthService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("register")
    @Transactional
    public ResponseEntity register(@RequestBody @Valid RegisterDto registerDto) {
        try {
            UserEntity user = authService.register(registerDto);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDto loginDto) {
        try {
            return ResponseEntity.ok(authService.login(loginDto));
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomErrorResponse("invalid credentials"), HttpStatus.UNAUTHORIZED);
        }
    }
}
