package com.pucilowski.exchange.main.service.impl;

import com.pucilowski.exchange.common.enums.OrderSide;
import com.pucilowski.exchange.common.enums.OrderStatus;
import com.pucilowski.exchange.integration.model.out.TradeExecuted;
import com.pucilowski.exchange.integration.service.client.TradeListener;
import com.pucilowski.exchange.main.persistence.entity.Order;
import com.pucilowski.exchange.main.persistence.entity.Trade;
import com.pucilowski.exchange.main.persistence.repository.OrderRepository;
import com.pucilowski.exchange.main.persistence.repository.TradeRepository;
import com.pucilowski.exchange.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by martin on 19/05/14.
 */

@Component("tradeListener")
public class TradeListenerImpl implements TradeListener {

    @Autowired
    TradeRepository tradeRepository;

    @Autowired
    UserService userService;

    @Autowired
    OrderRepository orderRepository;

    //TODO do it with less queries
    @Override
    @Transactional //TODO check if the message pull and database ops are atomic
    public void tradeExecuted(TradeExecuted trade) {
        System.out.println("In: " + trade);

        Order bid = orderRepository.findOne(trade.getBidOrderId());
        Order ask = orderRepository.findOne(trade.getAskOrderId());

        bid.setRemaining(bid.getRemaining() - trade.getQuantity());
        if (bid.getRemaining() == 0) {
            bid.setStatus(OrderStatus.FULFILLED);
            bid.setClosed(new Date());
        }

        ask.setRemaining(ask.getRemaining() - trade.getQuantity());
        if (ask.getRemaining() == 0) {
            ask.setStatus(OrderStatus.FULFILLED);
            ask.setClosed(new Date());
        }

        orderRepository.save(Arrays.asList(bid, ask));


        OrderSide side = bid.getOpened().after(ask.getOpened()) ? OrderSide.BID : OrderSide.ASK;

        Trade t = new Trade();
        t.setMarket(bid.getMarket());
        t.setSide(side);
        t.setBidOrder(bid);
        t.setAskOrder(ask);
        t.setPrice(trade.getPrice());
        t.setQuantity(trade.getQuantity());
        t.setExecuted(new Date());
        tradeRepository.save(t);
    }

    //@Override
    public void orderCancelledConfirmation(Long id) {
        System.out.println("In: cancelling #" + id);

        Order order = orderRepository.findOne(id);
        order.setStatus(OrderStatus.CANCELLED);
        order.setClosed(new Date());

        orderRepository.save(order);
    }
}
