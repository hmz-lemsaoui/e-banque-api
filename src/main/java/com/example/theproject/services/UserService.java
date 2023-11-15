package com.example.theproject.services;


import com.example.theproject.models.UserEntity;
import com.example.theproject.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    // activate user
    public UserEntity switchUserActivation(int id) {
        UserEntity user = userRepo.findById(id).get();
        user.setActivated(!user.isActivated());
        userRepo.save(user);
        return user;
    }

}
