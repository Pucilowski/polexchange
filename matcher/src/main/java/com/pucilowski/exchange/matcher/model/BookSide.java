package com.pucilowski.exchange.matcher.model;


import com.pucilowski.exchange.common.enums.OrderSide;

import java.util.*;

/**
 * Created by martin on 20/01/14.
 */

/**
 * Essentially a stack
 */
public class BookSide {

    private final OrderSide side;

    TreeMap<Integer, PriceLevel> level = new TreeMap<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            int diff = o1 - o2;

            return side == OrderSide.BID ? -diff : diff;
        }
    });


    public int best;


    public BookSide(OrderSide side) {
        this.side = side;
    }

    public void push(Order o) {
        if (level.containsKey(o.price)) {
            level.get(o.price).push(o);
        } else {
            level.put(o.price, new PriceLevel(o));
        }

        best = o.price;
    }

    public Order pop() {
        if (level.isEmpty()) return null;

        Map.Entry<Integer, PriceLevel> topPriceLevel = level.pollFirstEntry();
        PriceLevel topOrders = topPriceLevel.getValue();

        if (topOrders.isEmpty()) return null;
        Order top = topOrders.pop();

        if (topOrders.isEmpty()) {
            level.remove(topPriceLevel.getKey());
            best = level.pollFirstEntry().getKey();
        }
        return top;
    }

    public Order peek() {
        if (level.isEmpty()) return null;

        Map.Entry<Integer, PriceLevel> topPriceLevel = level.pollFirstEntry();
        PriceLevel topOrders = topPriceLevel.getValue();

        if (topOrders.isEmpty()) return null;
        return topOrders.peek();
    }


    private class PriceLevel {
        private final int price;
        private final LinkedList<Order> orders = new LinkedList<Order>();

        public PriceLevel(Order order) {
            price = order.price;
        }

        public boolean isEmpty() {
            return orders.isEmpty();
        }

        public void push(Order o) {
            orders.push(o);
        }

        public Order pop() {
            return orders.pop();
        }

        public Order peek() {
            return orders.peek();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            PriceLevel that = (PriceLevel) o;

            return price == that.price;
        }

        @Override
        public int hashCode() {
            return price;
        }
    }

}
