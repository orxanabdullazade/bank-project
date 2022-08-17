package com.unibank.unitech.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
@Data
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double balance;

    private int status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
