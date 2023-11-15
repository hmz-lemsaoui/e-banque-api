package com.example.theproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor

public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String fullName;
    private String rib;
    private boolean isActivated;

    @JsonIgnore
    private String password;

    private double solde;


    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.REMOVE,CascadeType.MERGE,CascadeType.REFRESH} )
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))

    @JsonManagedReference
    private List<Role> roles= new ArrayList<>();

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Operation> senderOperations ;

    @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Operation> recipientOperations ;




}
