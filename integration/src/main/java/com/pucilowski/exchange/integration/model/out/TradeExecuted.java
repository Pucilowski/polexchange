package com.pucilowski.exchange.integration.model.out;

/**
 * Created by martin on 13/05/14.
 */
public class TradeExecuted {

    int price;
    int quantity;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
