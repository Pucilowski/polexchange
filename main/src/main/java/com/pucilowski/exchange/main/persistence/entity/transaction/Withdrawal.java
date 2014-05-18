package com.pucilowski.exchange.main.persistence.entity.transaction;



import com.pucilowski.exchange.main.persistence.entity.Currency;
import com.pucilowski.exchange.main.persistence.entity.User;

import java.util.Date;

/**
 * Created by martin on 04/02/14.
 */
public class Withdrawal {

    User user;
    Currency currency;


    public String address;
    public int value;



    public Date requested;

    public int confirmations;

    public Date fulfilled;

}
