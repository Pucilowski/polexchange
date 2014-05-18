package com.pucilowski.exchange.main.persistence.entity;

import javax.persistence.*;

/**
 * Created by Martin on 29/01/14.
 */

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;

    public String email;

    public String password;

    //@OneToMany
    //@JoinColumn(name = "id")
    //Set<Balance> balanceSet;

}
