package com.example.theproject.repository;

import com.example.theproject.models.Operation;
import com.example.theproject.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OperationRepo extends JpaRepository<Operation, Integer> {
    
    List<UserEntity> findBySenderId(int senderId);
    List<UserEntity> findByRecipientId(int recipientId);
}
