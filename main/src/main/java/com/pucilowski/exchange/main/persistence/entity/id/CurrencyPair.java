package com.pucilowski.exchange.main.persistence.entity.id;

import com.pucilowski.exchange.main.persistence.entity.Currency;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by martin on 04/02/14.
 */

@Embeddable
public class CurrencyPair implements Serializable {

    @ManyToOne
    private Currency base;

    @ManyToOne
    private Currency counter;

    public CurrencyPair() {
    }

    public CurrencyPair(Currency base, Currency counter) {
        this.base = base;
        this.counter = counter;
    }

    public CurrencyPair(String base, String counter) {
        this(new Currency(base),new Currency(counter));
    }

    public void setBase(Currency base) {
        this.base = base;
    }

    public Currency getBase() {
        return base;
    }


    public void setCounter(Currency counter) {
        this.counter = counter;
    }

    public Currency getCounter() {
        return counter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CurrencyPair that = (CurrencyPair) o;

        if (base != null ? !base.equals(that.base) : that.base != null) return false;
        if (counter != null ? !counter.equals(that.counter) : that.counter != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = base.hashCode();
        result = 31 * result + counter.hashCode();
        return result;
    }
}
