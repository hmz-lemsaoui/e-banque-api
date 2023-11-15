package com.example.theproject.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double montant;

    private String type;

    private String date;

    @ManyToOne
    @JoinColumn(name="sender_id")
    private UserEntity sender;

    @ManyToOne
    @JoinColumn(name="recipient_id")
    private UserEntity recipient;


}
