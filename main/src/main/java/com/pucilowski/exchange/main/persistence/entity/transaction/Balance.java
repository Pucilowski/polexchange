package com.pucilowski.exchange.main.persistence.entity.transaction;

import com.pucilowski.exchange.main.persistence.entity.id.UserCurrency;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by martin on 08/01/14.
 */


@Entity
@Table(name = "balances")
public class Balance implements Serializable {

/*    @Id
    @ManyToOne
    //@JoinColumn(name = "user_id")
    public User user;

    @Id
    @ManyToOne
    //@JoinColumn(name = "currency_id")
    public Currency currency;*/

    @EmbeddedId
    UserCurrency userCurrency;

    public int value;
    public int unconfirmed;
    public int frozen;

    @Temporal(TemporalType.TIMESTAMP)
    public Date submitted;


    public String toString() {
        StringBuilder sb = new StringBuilder();

        return sb.toString();
    }
}
