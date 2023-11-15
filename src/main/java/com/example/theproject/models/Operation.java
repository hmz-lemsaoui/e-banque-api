package com.example.theproject.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "operations")
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
    @JsonBackReference
    private UserEntity sender;

    @ManyToOne
    @JoinColumn(name="recipient_id")
    @JsonBackReference
    private UserEntity recipient;


}
