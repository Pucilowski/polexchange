package com.pucilowski.exchange.main.service.impl;


import com.pucilowski.exchange.api.user.request.SubmitOrder;
import com.pucilowski.exchange.common.enums.OrderStatus;
import com.pucilowski.exchange.integration.model.in.OrderCancelled;
import com.pucilowski.exchange.integration.model.in.OrderSubmitted;
import com.pucilowski.exchange.integration.service.client.MatcherClient;
import com.pucilowski.exchange.main.persistence.entity.Market;
import com.pucilowski.exchange.main.persistence.entity.Order;
import com.pucilowski.exchange.main.persistence.entity.Trade;
import com.pucilowski.exchange.main.persistence.entity.id.CurrencyPair;
import com.pucilowski.exchange.main.persistence.repository.OrderRepository;
import com.pucilowski.exchange.main.persistence.repository.TradeRepository;
import com.pucilowski.exchange.main.service.UserService;
import com.pucilowski.exchange.main.util.Converters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;

/**
 * Created by Martin on 29/01/14.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    TradeRepository tradeRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    MatcherClient matcherClient;

    @Override
    @Transactional
    public OrderSubmitted submitOrder(SubmitOrder dto) {
        String base = dto.getBase();
        String counter = dto.getCounter();
        CurrencyPair pair = Converters.toCurrency(base, counter);

        Market m = new Market();
        m.setId(pair);

        Order order = new Order();
        order.user = null; //get user from context
        order.market = m;
        order.side = dto.getSide();
        order.price = dto.getPrice();
        order.quantity = dto.getQuantity();
        order.remaining = dto.getQuantity();
        order.status = OrderStatus.OPEN;
        order.opened = new Date();
        orderRepository.save(order);


        OrderSubmitted s = new OrderSubmitted();
        s.id = order.id;
        s.side = order.side;
        s.price = order.price;
        s.quantity = order.quantity;
        matcherClient.submitOrder(base, counter, s);

        return s;
    }

    @Override
    @Transactional
    public void cancelOrder(Long id) {
        Order o = orderRepository.findOne(id);

        o.setStatus(OrderStatus.CANCELLING);

        orderRepository.save(o);

        String base = o.getMarket().getId().getBase().getCode();
        String counter = o.getMarket().getId().getCounter().getCode();

        OrderCancelled oc = new OrderCancelled(id);
        matcherClient.cancelOrder(base, counter, oc);
    }

    @Override
    public Collection<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Collection<Trade> getTrades() {
        return tradeRepository.findAll();
    }
}
