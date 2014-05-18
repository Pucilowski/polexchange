package com.pucilowski.exchange.matcher.integration.client;

import com.pucilowski.exchange.matcher.integration.model.in.OrderCancelled;
import com.pucilowski.exchange.matcher.integration.model.in.OrderSubmitted;
import com.pucilowski.exchange.matcher.integration.model.out.TradeExecuted;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Martin on 29/01/14.
 */

@Service
@Transactional
public class MatcherMessagingImpl implements MatcherMessaging {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    RabbitAdmin rabbitAdmin;

    @Override
    @Transactional
    public void submitOrder(String base, String counter, OrderSubmitted order) {
        StringBuilder sb = new StringBuilder("order.submitted.").append(base).append(".").append(counter);
        String queueName = sb.toString().toLowerCase();

        Exchange e = new DirectExchange("orders");
        Queue q = new Queue(queueName);

        rabbitAdmin.declareExchange(e);
        rabbitAdmin.declareQueue(q);
        rabbitAdmin.declareBinding(BindingBuilder.bind(q).to(e).with(queueName).noargs());

        rabbitTemplate.setQueue(queueName);
        //rabbit.setRoutingKey(sb.toString());

        //System.out.println("Sending to : " + queueName);
        rabbitTemplate.convertAndSend(queueName, order);
    }

    @Override
    @Transactional
    public void cancelOrder(String base, String counter, OrderCancelled order) {
        StringBuilder sb = new StringBuilder("order.cancelled.").append(base).append(".").append(counter);
        String queueName = sb.toString().toLowerCase();

        Exchange e = new DirectExchange("orders");
        Queue q = new Queue(queueName);

        rabbitAdmin.declareExchange(e);
        rabbitAdmin.declareQueue(q);
        rabbitAdmin.declareBinding(BindingBuilder.bind(q).to(e).with(queueName).noargs());

        rabbitTemplate.setQueue(queueName);
        //rabbit.setRoutingKey(sb.toString());

        //System.out.println("Sending to : " + queueName);
        rabbitTemplate.convertAndSend(queueName, order);
    }


    @Override
    public void tradeExecuted(TradeExecuted trade) {
        System.out.println("New trade: " + trade);



    }


}
