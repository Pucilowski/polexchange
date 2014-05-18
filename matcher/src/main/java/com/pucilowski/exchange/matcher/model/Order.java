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
    final public OrderSide side;

    final public int price;
    final public int quantity;

    public int remaining;

    public Order(long id, OrderSide side, int price, int quantity) {
        this.id = id;
        this.side = side;
        this.price = price;
        this.quantity = quantity;
        this.remaining = quantity;
    }

    public Order(OrderSubmitted order) {
        this(order.id, order.side, order.price, order.quantity);
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
