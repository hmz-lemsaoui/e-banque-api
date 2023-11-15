package com.example.theproject.services;


import com.example.theproject.models.Operation;
import com.example.theproject.models.UserEntity;
import com.example.theproject.repository.OperationRepo;
import com.example.theproject.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OperationService {


    private UserRepo userRepo;
    private OperationRepo operationRepo;

    @Autowired
    public OperationService(UserRepo userRepo, OperationRepo operationRepo) {
        this.userRepo = userRepo;
        this.operationRepo = operationRepo;
    }

    public Operation sendMoney(int senderId, String rib, double amount) {

        UserEntity sender = userRepo.findById(senderId).get();
        UserEntity recipient = userRepo.findByRib(rib).get();

        if (sender.isActivated() == false || recipient.isActivated() == false){
            throw new RuntimeException("Account not activated");
        }

        if (sender.getSolde() < amount ){
            throw new RuntimeException("Insufficient funds");
        }

        sender.setSolde(sender.getSolde() - amount);

        recipient.setSolde(recipient.getSolde() + amount);

        userRepo.save(sender);
        userRepo.save(recipient);

        Operation operation = new Operation();
        operation.setSender(sender);
        operation.setRecipient(recipient);
        operation.setMontant(amount);
        operation.setDate(String.valueOf(new Date()));
        operationRepo.save(operation);
        return operation;
    }

    // get operations by sender id
    public List<UserEntity> getOperationsBySenderId(int senderId) {
        return operationRepo.findBySenderId(senderId);
    }

    // get operations by recipient id
    public List<UserEntity> getOperationsByRecipientId(int recipientId) {
        return operationRepo.findByRecipientId(recipientId);
    }
}
