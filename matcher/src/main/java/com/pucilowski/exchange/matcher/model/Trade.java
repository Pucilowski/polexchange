package com.pucilowski.exchange.matcher.model;

/**
 * Created by martin on 20/01/14.
 */
public class Trade {

    public final Order bidOrder;
    public final Order askOrder;

    public final int price;
    public final int quantity;

    public Trade(Order bidOrder, Order askOrder, int price, int quantity) {
        this.bidOrder = bidOrder;
        this.askOrder = askOrder;
        this.price = price;
        this.quantity = quantity;
    }
}
