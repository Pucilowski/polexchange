package com.pucilowski.exchange.api.request;


import com.pucilowski.exchange.common.enums.OrderSide;

/**
 * Created by Martin on 29/01/14.
 */

//@JsonAutoDetect
public class SubmitOrder {

    private String base;
    private String counter;

    private OrderSide side;
    private int price;
    private int quantity;

    public SubmitOrder() {
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
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

    public SubmitOrder(String base, String counter, OrderSide side, int price, int quantity) {
        this.base=base;
        this.counter=counter;

        this.side=side;
        this.price=price;
        this.quantity=quantity;
    }

}
