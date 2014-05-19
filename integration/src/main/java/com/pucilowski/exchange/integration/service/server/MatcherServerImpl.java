package com.pucilowski.exchange.integration.service.server;

import com.pucilowski.exchange.integration.model.in.OrderCancelled;
import com.pucilowski.exchange.integration.model.in.OrderSubmitted;
import com.pucilowski.exchange.integration.model.out.TradeExecuted;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: martin
 * Date: 02/12/13
 * Time: 07:29
 * To change this template use File | Settings | File Templates.
 */

@Service
public class MatcherServerImpl implements MatcherServer {

    @Value("${exchange.name}")
    String exchangeName;
    @Value("${queue.trade.executed.name}")
    String executedQueue;

    @Autowired
    RabbitAdmin rabbitAdmin;
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void tradeExecuted(TradeExecuted trade) {
        Exchange e = new DirectExchange(exchangeName);
        Queue q = new Queue(executedQueue);

        rabbitAdmin.declareExchange(e);
        rabbitAdmin.declareQueue(q);
        rabbitAdmin.declareBinding(BindingBuilder.bind(q).to(e).with(executedQueue).noargs());

        rabbitTemplate.setQueue(executedQueue);
        //rabbit.setRoutingKey(sb.toString());

        System.out.println("Sending " + trade + " to " + executedQueue);
        rabbitTemplate.convertAndSend(executedQueue, trade);
    }
}
