package com.example.theproject.services;


import com.example.theproject.config.TokenGenerator;
import com.example.theproject.dto.AuthresponseDto;
import com.example.theproject.dto.CustomErrorResponse;
import com.example.theproject.dto.LoginDto;
import com.example.theproject.dto.RegisterDto;
import com.example.theproject.models.Role;
import com.example.theproject.models.UserEntity;
import com.example.theproject.repository.RoleRepo;
import com.example.theproject.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {


    private AuthenticationManager authenticationManager;
    private UserRepo userRepo;
    private RoleRepo roleRepo;
    private PasswordEncoder passwordEncoder;
    private TokenGenerator tokenGenerator;


    @Autowired
    public AuthService(AuthenticationManager authenticationManager, UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder, TokenGenerator tokenGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
        this.tokenGenerator = tokenGenerator;
    }

    public UserEntity register(RegisterDto registerDto) {
        if (userRepo.existsByEmail(registerDto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        UserEntity user = new UserEntity();
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        List roles = new ArrayList();
        Role role = roleRepo.findByName("user").get();
        roles.add(role);
        user.setRoles(roles);
        return userRepo.save(user);

    }


    public AuthresponseDto login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                ));

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = tokenGenerator.generateToken(userDetails);
        UserEntity user = userRepo.findByEmail(loginDto.getEmail()).get();
        return new AuthresponseDto(token, user);
    }


}
