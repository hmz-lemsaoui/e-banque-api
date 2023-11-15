package com.example.theproject.services;


import com.example.theproject.dto.UserDto;
import com.example.theproject.models.UserEntity;
import com.example.theproject.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Service
public class UserService {


    private UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo
    ) {
        this.userRepo = userRepo;
    }

    // get all users
    public List<UserEntity> getAllUsers() {
        return userRepo.findAll();
    }

    // get user by id
    public UserEntity getUserById(int id) {
        return userRepo.findById(id).get();
    }

    // get user by rib
    public UserEntity getUserByRib(String rib) {
        return userRepo.findByRib(rib).get();
    }


    public UserEntity updateUser(UserDto userDto) {
        UserEntity user = userRepo.findById(userDto.getId()).get();
        // check if user exists
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        user.setEmail(userDto.getEmail());
        user.setFullName(userDto.getFullName());
        user.setRib(userDto.getRib());
        user.setActivated(userDto.isActivated());
        return userRepo.save(user);
    }

    // find user by email
    public UserEntity findByEmail(String email) {
        return userRepo.findByEmail(email).get();
    }
}
