package com.pucilowski.exchange.main.web.api.request;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.pucilowski.exchange.common.enums.OrderSide;

/**
 * Created by Martin on 29/01/14.
 */

@JsonAutoDetect
public class SubmitOrder {

    private String base;
    private String counter;

    private OrderSide side;
    private int price;
    private int quantity;

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

    public OrderSide getSide() {
        return side;
    }

    public void setSide(OrderSide side) {
        this.side = side;
    }

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }


    public static SubmitOrder toDto(String base, String counter, OrderSide side, int price, int quantity) {
        SubmitOrder order = new SubmitOrder();

        order.setBase(base);
        order.setCounter(counter);

        order.setSide(side);
        order.setPrice(price);
        order.setQuantity(quantity);


        return order;
    }

}
