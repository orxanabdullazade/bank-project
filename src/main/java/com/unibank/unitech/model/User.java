package com.unibank.unitech.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String pin;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<Account> accounts;
}
