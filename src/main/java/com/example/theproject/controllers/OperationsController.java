package com.example.theproject.controllers;

import com.example.theproject.dto.CustomErrorResponse;
import com.example.theproject.dto.OperationDto;
import com.example.theproject.models.UserEntity;
import com.example.theproject.services.OperationService;
import com.example.theproject.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/operations")
@Validated

public class OperationsController {

    private OperationService operationService;
    private UserService userService;

    public OperationsController(OperationService operationService
            , UserService userService
    ) {
        this.operationService = operationService;
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('user')")
    @PostMapping("")
    public ResponseEntity createOperation(@RequestBody @Valid OperationDto operationDto) {

        try {


            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String email = userDetails.getUsername();

            UserEntity currentUser = userService.findByEmail(email);

            return ResponseEntity.ok(
                    operationService.sendMoney(
                            currentUser.getId(),
                            operationDto.getRecipientRib(),
                            operationDto.getMontant()
                    )
            );

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
