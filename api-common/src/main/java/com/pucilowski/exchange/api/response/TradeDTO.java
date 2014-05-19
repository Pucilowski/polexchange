package com.pucilowski.exchange.api.response;

import com.pucilowski.exchange.common.enums.OrderSide;

import java.util.Date;

/**
 * Created by martin on 06/02/14.
 */

//@JsonAutoDetect
public class TradeDTO {

    private OrderSide side;
    private int price;
    private int quantity;
    private Date executed;

    public TradeDTO() {

    }

    public OrderSide getSide() {
        return side;
    }

    public void setSide(OrderSide side) {
        this.side = side;
    }

    public Date getExecuted() {
        return executed;
    }

    public void setExecuted(Date executed) {
        this.executed = executed;
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
