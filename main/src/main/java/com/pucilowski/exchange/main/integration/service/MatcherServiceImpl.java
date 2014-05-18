package com.pucilowski.exchange.main.integration.service;

import com.pucilowski.exchange.main.persistence.entity.Order;
import com.pucilowski.exchange.main.persistence.entity.Trade;
import com.pucilowski.exchange.main.persistence.entity.id.CurrencyPair;
import com.pucilowski.exchange.common.enums.OrderSide;
import com.pucilowski.exchange.common.enums.OrderStatus;
import com.pucilowski.exchange.main.persistence.repository.OrderRepository;
import com.pucilowski.exchange.main.persistence.repository.TradeRepository;
import com.pucilowski.exchange.main.integration.service.MatcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Martin on 29/01/14.
 */

//@Service
//@Transactional
public class MatcherServiceImpl implements MatcherService {

    //@Autowired
    OrderRepository orderRepository;

    //@Autowired
    TradeRepository tradeRepository;


    @Override
    @Transactional
    public ArrayList<Trade> processPendingOrders(String base, String counter) {
        CurrencyPair id = new CurrencyPair(base, counter);

        Collection<Order> pendingOrders = orderRepository.getPendingOrders(id);

        ArrayList<Order> bids = orderRepository.getOpenBidOrders(id);
        ArrayList<Order> asks = orderRepository.getOpenAskOrders(id);


        ArrayList<Trade> resultantTrades = new ArrayList<>();


        // freshest entries get processed first
        for (Order pending : pendingOrders) {

            ArrayList<Order> openOrders = pending.side == OrderSide.BID ?
                    asks : bids;

            processPending(pending, openOrders);

        }

        return resultantTrades;
    }

    @Override
    @Transactional
    public void processPending(Order pending, List<Order> openOrders) {


        ArrayList<Order> ordersToSave = new ArrayList<>();
        ArrayList<Trade> tradesToSave = new ArrayList<>();

        for (Order open : openOrders) {
            Trade result = match(pending,open);

            if (result == null) {
                break;
            }

            tradesToSave.add(result);
            ordersToSave.add(open);

            if (open.remaining == 0) {
                open.status = OrderStatus.COMPLETE;
            }
        }

        if (pending.remaining == 0) {
            pending.status = OrderStatus.COMPLETE;
        } else pending.status = OrderStatus.OPEN;

        ordersToSave.add(pending);

        orderRepository.save(ordersToSave);
        tradeRepository.save(tradesToSave);
    }

    @Override
    @Transactional
    public Trade match(Order pending, Order open) {
        Order bid, ask;

        if (pending.side == OrderSide.BID) {
            bid = pending;
            ask = open;
        } else {
            bid = open;
            ask = pending;
        }

        if (bid.price < ask.price) return null;
        if(bid.remaining == 0 || ask.remaining==0) return null;

        System.out.println("=====================");
        System.out.printf("Bid id: %s, price: %s, qty: %s\n", bid.id, bid.price, bid.remaining);
        System.out.printf("Ask id: %s, price: %s, qty: %s\n", ask.id, ask.price, ask.remaining);

        int quantity = Math.min(pending.remaining, open.remaining);
        pending.remaining -= quantity;
        open.remaining -= quantity;

        Trade trade = new Trade();
        trade.market = pending.market;
        trade.side = pending.side;
        trade.bid = bid;
        trade.ask = ask;
        trade.quantity = quantity;
        trade.price = open.price;
        trade.executed = new Date();

        System.out.println("Match made:");
        System.out.printf("Trade price: %s, quantity: %s\n", trade.price, trade.quantity);

        System.out.println("Result");
        System.out.printf("Bid id: %s, price: %s, qty: %s\n", bid.id, bid.price, bid.remaining);
        System.out.printf("Ask id: %s, price: %s, qty: %s\n", ask.id, ask.price, ask.remaining);
        System.out.println("=====================");
        System.out.println("");

        return trade;
    }


}
