package com.pucilowski.exchange.matcher.integration.server;

import com.pucilowski.exchange.matcher.integration.model.in.OrderCancelled;
import com.pucilowski.exchange.matcher.integration.model.in.OrderSubmitted;
import com.pucilowski.exchange.matcher.matching.model.Order;
import com.pucilowski.exchange.matcher.matching.model.Trade;
import com.pucilowski.exchange.matcher.matching.service.Matcher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: martin
 * Date: 02/12/13
 * Time: 07:29
 * To change this template use File | Settings | File Templates.
 */

@Service
public class MatchBroadcasterImpl implements MatchBroadcaster {

    @Autowired
    Matcher matcher;

    @Autowired
    RabbitTemplate rabbit;

    @Override
    public void broadcastTrade(Trade trade) {

        //rabbit.send();

    }


    @Override
    public void orderSubmitted(OrderSubmitted order) {
        System.out.println("Order submitted: " + order);

        matcher.inputOrder(new Order(order));
    }

    @Override
    public void orderCancelled(OrderCancelled order) {
        System.out.println("Order cancelled: " + order);

        matcher.cancelOrder(order.getId());
    }
}
