package com.pucilowski.exchange.integration.model.in;

import com.pucilowski.exchange.common.enums.OrderSide;

/**
 * Created by martin on 13/05/14.
 */
public class OrderSubmitted {

    public long id;
    public OrderSide side;

    public int price;
    public int quantity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
