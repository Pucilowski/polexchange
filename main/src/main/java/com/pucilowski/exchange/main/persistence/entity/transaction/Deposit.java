package com.pucilowski.exchange.main.persistence.entity.transaction;

import com.pucilowski.exchange.main.persistence.entity.Currency;
import com.pucilowski.exchange.main.persistence.entity.User;

import javax.persistence.OneToOne;
import java.util.Date;

/**
 * Created by martin on 04/02/14.
 */
public class Deposit {

    User user;
    Currency currency;


    public String address;
    public String txId;
    public int outpout;

    int value;

    public Date spotted;
    public Date confirmed;


    @OneToOne
    Transaction tx;
}
