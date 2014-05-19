package com.pucilowski.exchange.main.service;


import com.pucilowski.exchange.common.enums.OrderStatus;
import com.pucilowski.exchange.integration.service.client.MatcherClient;
import com.pucilowski.exchange.main.persistence.entity.Currency;
import com.pucilowski.exchange.main.persistence.entity.Market;
import com.pucilowski.exchange.main.persistence.entity.Order;
import com.pucilowski.exchange.main.persistence.entity.Trade;
import com.pucilowski.exchange.main.persistence.entity.id.CurrencyPair;
import com.pucilowski.exchange.main.persistence.repository.OrderRepository;
import com.pucilowski.exchange.main.persistence.repository.TradeRepository;
import com.pucilowski.exchange.main.web.api.request.CancelOrder;
import com.pucilowski.exchange.main.web.api.request.SubmitOrder;
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
    public void submitOrder(SubmitOrder dto) {
        String base = dto.getBase();//.toLowerCase();
        String counter = dto.getCounter();//.toLowerCase();


        Order order = new Order();

        Market m = new Market();
        m.setId(new CurrencyPair(new Currency(base), new Currency(counter)));

        order.user = null; //get user from context
        order.market = m;
        order.side = dto.getSide();

        order.quantity = dto.getQuantity();
        order.price = dto.getPrice();
        order.remaining = dto.getQuantity();

        order.status = OrderStatus.PENDING;
        order.submitted = new Date();

        orderRepository.save(order);



     /*   SubmitOrder s = new SubmitOrder();
        s.id = order.id;
        s.side = order.side;
        s.price = order.price;
        s.quantity = order.quantity;*/

        //messageQueue.submitOrder(base, counter, s);

    }

    @Override
    public void cancelOrder(CancelOrder dto) {


        //orderRepository.delete(dto.getId());
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
