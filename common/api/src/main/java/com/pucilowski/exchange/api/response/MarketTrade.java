package com.pucilowski.exchange.api.response;

import com.pucilowski.exchange.common.enums.OrderSide;

import java.util.Date;

/**
 * Created by martin on 06/02/14.
 */

//@JsonAutoDetect
public class MarketTrade {

    private OrderSide side;
    private int price;
    private int quantity;
    private Date executed;

    public MarketTrade() {

    }

    public MarketTrade(OrderSide side, int price, int quantity, Date executed) {
        this.side = side;
        this.price = price;
        this.quantity = quantity;
        this.executed = executed;
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

    public Date getExecuted() {
        return executed;
    }

    public void setExecuted(Date executed) {
        this.executed = executed;
    }
}
