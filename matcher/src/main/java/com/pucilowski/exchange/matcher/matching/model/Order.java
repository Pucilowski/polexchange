package com.pucilowski.exchange.matcher.matching.model;


import com.pucilowski.exchange.common.enums.OrderSide;
import com.pucilowski.exchange.matcher.integration.in.OrderSubmitted;

/**
 * Created with IntelliJ IDEA.
 * User: martin
 * Date: 02/12/13
 * Time: 07:30
 * Models an open order
 */
public class Order implements Comparable {

    public long id;

    public OrderSide side;

    public int price;
    public int quantity;

    public int remaining;

    public Order() {

    }

    public Order(OrderSubmitted order) {
        this.id = order.id;
        this.side = order.side;
        this.price = order.price;
        this.quantity = order.quantity;
        this.remaining = order.quantity;
    }


    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
