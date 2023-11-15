package com.example.theproject.controllers;

import com.example.theproject.dto.CustomErrorResponse;
import com.example.theproject.dto.UserDto;
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
    @PutMapping("{id}")
    public ResponseEntity updateUser(@RequestBody UserDto userDto, @PathVariable int id) {
        if (userDto.getId() != id) {
            return ResponseEntity.badRequest().body(new CustomErrorResponse("User id does not match"));
        }
        try {
            return ResponseEntity.ok(userService.updateUser(userDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new CustomErrorResponse("User could not be updated"));
        }
    }


}
