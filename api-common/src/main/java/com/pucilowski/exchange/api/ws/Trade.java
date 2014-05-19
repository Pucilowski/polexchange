package com.pucilowski.exchange.api.ws;

import com.pucilowski.exchange.common.enums.OrderSide;

/**
 * Created by martin on 19/05/14.
 */
public class Trade {
    private OrderSide side;
    private int price;
    private int quantity;

    public OrderSide getSide() {
        return side;
    }

    public void setSide(OrderSide side) {
        this.side = side;
    }

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
