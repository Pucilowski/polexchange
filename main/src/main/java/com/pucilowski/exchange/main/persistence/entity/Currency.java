package com.pucilowski.exchange.main.persistence.entity;

import javax.persistence.*;

/**
 * Created by martin on 08/01/14.
 */

@Entity
@Table(name = "currencies")
public class Currency {

    @Id
    private String code;

    private String name;

    public Currency() {
    }

    public Currency(String code) {
        this.code = code;
    }

    public Currency(String code, String name) {
        this.code = code;
        this.name = name;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Currency currency = (Currency) o;

        if (code != null ? !code.equals(currency.code) : currency.code != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return code != null ? code.hashCode() : 0;
    }
}
