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

    TreeMap<Integer, PriceLevel> levels = new TreeMap<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            int diff = o1 - o2;

            return side == OrderSide.BID ? -diff : diff;
        }
    });


    private BookSide(OrderSide side) {
        this.side = side;
    }

    private int best;

    protected int getBest() {
        Order best = getBestOrder();
        if (best == null) return -1;
        return best.getPrice();
    }

    public Order bestOffer(int price) {
        Order o = getBestOrder();
        if (o == null) return null;

        if (satisfies(price, o.getPrice()))
            return o;

        return null;
    }

    public boolean satisfies(int offer, int offer2) {
        return side == OrderSide.BID ? offer >= offer2 : offer2 <= offer;
    }

    public void insert(Order o) {
        if (levels.containsKey(o.price)) {
            levels.get(o.price).insert(o);
        } else {
            levels.put(o.price, new PriceLevel(o));
        }

        //best = o.price;
    }

    public Order removeBestOrder() {
        if (levels.isEmpty()) return null;

        PriceLevel bestLevel = levels.firstEntry().getValue();

        Order top = bestLevel.removeOldest();
        if (bestLevel.isEmpty()) {
            levels.pollFirstEntry();
        }
        return top;
    }

    public Order getBestOrder() {
        if (levels.isEmpty()) return null;

        PriceLevel bestLevel = levels.firstEntry().getValue();

        if (bestLevel.isEmpty()) return null;
        return bestLevel.getOldest();
    }

    public Map<Integer, List<Integer>> getBook() {
        Map<Integer, List<Integer>> book= new LinkedHashMap<>();

        for(PriceLevel level : levels.values()) {
            List<Integer> volumes = new ArrayList<>();

            LinkedList<Order> orders = level.orders;
            for (Order order : orders) {
                volumes.add(order.getRemaining());
            }

            book.put(level.price, volumes);
        }

        return book;
    }

    public void dumpString() {
        for(PriceLevel level : levels.values()) {
            System.out.println("Price: " + level.price);
            for(Order order : level.orders) {
                System.out.println("\t"+order.getRemaining() + " @ " + order.getPrice());
            }
        }
    }



    private class PriceLevel {
        private final int price;
        private final LinkedList<Order> orders = new LinkedList<Order>();

        public PriceLevel(Order order) {
            price = order.price;
            orders.push(order);
        }

        public boolean isEmpty() {
            return orders.isEmpty();
        }

        public void insert(Order o) {
            orders.addLast(o);
        }

        public Order removeOldest() {
            return orders.pollFirst();
        }

        public Order getOldest() {
            return orders.peekFirst();
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

        public int size() {
            return orders.size();
        }
    }

    public static class Bids extends BookSide {
        public Bids() {
            super(OrderSide.BID);
        }

        public int bestBid() {
            return getBest();
        }
    }

    public static class Asks extends BookSide {
        public Asks() {
            super(OrderSide.ASK);
        }

        public int bestAsk() {
            return getBest();
        }
    }
}
