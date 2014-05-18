package com.pucilowski.exchange.main.persistence.entity.id;

import com.pucilowski.exchange.main.persistence.entity.Currency;
import com.pucilowski.exchange.main.persistence.entity.User;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by martin on 04/02/14.
 */

@Embeddable
public class UserCurrency implements Serializable {

    @ManyToOne
    private User user;

    @ManyToOne
    private Currency currency;

    public UserCurrency() {
    }

    public UserCurrency(User user, Currency currency) {
        this.user = user;
        this.currency = currency;
    }

    public void setUser(User base) {
        this.user = base;
    }

    public User getUser() {
        return user;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserCurrency that = (UserCurrency) o;

        if (currency != null ? !currency.equals(that.currency) : that.currency != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + currency.hashCode();
        return result;
    }
}
