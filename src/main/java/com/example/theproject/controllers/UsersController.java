package com.example.theproject.controllers;

import com.example.theproject.dto.CustomErrorResponse;
import com.example.theproject.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Validated

public class UsersController {

    private UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    // get all users
    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("")
    public ResponseEntity getAllUsers() {
        try {
            return ResponseEntity.ok(userService.getAllUsers());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new CustomErrorResponse("Users could not be retrieved"));
        }
    }


    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/switch/{id}")
    public ResponseEntity UserActivation(@PathVariable int id) {
        try {
            return ResponseEntity.ok(userService.switchUserActivation(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new CustomErrorResponse("User could not be activated"));
        }
    }


}
