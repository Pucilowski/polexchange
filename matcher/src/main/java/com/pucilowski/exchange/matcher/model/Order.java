package com.pucilowski.exchange.matcher.model;

import com.pucilowski.exchange.common.enums.OrderSide;
import com.pucilowski.exchange.integration.model.in.OrderSubmitted;

/**
 * Created with IntelliJ IDEA.
 * User: martin
 * Date: 02/12/13
 * Time: 07:30
 * Models an open order
 */
public class Order implements Comparable {

    final long id;
    public final OrderSide side;
    final int price;
    //final public int quantity;

    private int remaining;

    public Order(long id, OrderSide side, int price, int quantity) {
        this.id = id;
        this.side = side;
        this.price = price;
        //this.quantity = quantity;
        this.remaining = quantity;
    }

    public Order(OrderSubmitted order) {
        this(order.id, order.side, order.price, order.quantity);
    }

    public long getId() {
        return id;
    }

    public OrderSide getSide() {
        return side;
    }

    public int getPrice() {
        return price;
    }

    public int getRemaining() {
        return remaining;
    }

    public void takeRemaining(int units) {
        if(units > remaining) throw new RuntimeException("trying to overfil order");
        this.remaining -= units;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", side=" + side +
                ", price=" + price +
                ", remaining=" + remaining +
                '}';
    }
}
