package com.pucilowski.exchange.integration.model.in;

/**
 * Created by martin on 13/05/14.
 */
public class OrderCancelled {

    public long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "OrderCancelled{" +
                "id=" + id +
                '}';
    }
}
