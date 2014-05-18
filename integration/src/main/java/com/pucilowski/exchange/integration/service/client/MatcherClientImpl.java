package com.pucilowski.exchange.integration.service.client;

import com.pucilowski.exchange.integration.model.in.OrderCancelled;
import com.pucilowski.exchange.integration.model.in.OrderSubmitted;
import com.pucilowski.exchange.integration.model.out.TradeExecuted;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Martin on 29/01/14.
 */

@Service
@Transactional
public class MatcherClientImpl implements MatcherClient {

    @Value("${exchange.name}")
    String exchangeName;
    @Value("${queue.order.submitted.name}")
    String submittedQueue;
    @Value("${queue.order.cancelled.name}")
    String cancelledQueue;

    @Autowired
    RabbitAdmin rabbitAdmin;
    @Autowired
    RabbitTemplate rabbitTemplate;
    //@Autowired
    //TradeService tradeService;

    @Override
    @Transactional
    public void submitOrder(String base, String counter, OrderSubmitted order) {
        final String queueName = (submittedQueue + "." + base + "." + counter).toLowerCase();

        Exchange e = new DirectExchange(exchangeName);
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
        final String queueName = (cancelledQueue + "." + base + "." + counter).toLowerCase();

        Exchange e = new DirectExchange(exchangeName);
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
