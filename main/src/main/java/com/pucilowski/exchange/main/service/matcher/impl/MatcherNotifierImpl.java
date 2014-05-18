package com.pucilowski.exchange.main.service.matcher.impl;

import com.pucilowski.exchange.main.service.matcher.MatcherNotifier;
import com.pucilowski.exchange.main.web.api.request.CancelOrder;
import com.pucilowski.exchange.main.web.api.request.SubmitOrder;
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
public class MatcherNotifierImpl implements MatcherNotifier {

    @Autowired
    RabbitTemplate rabbit;

    @Autowired
    RabbitAdmin rabbitAdmin;

    @Override
    @Transactional
    public void submitOrder(String base, String counter, SubmitOrder order) {
        StringBuilder sb = new StringBuilder("orders.submit.").append(base).append(".").append(counter);

        Exchange e = new DirectExchange("orders");

        String queueName = sb.toString().toLowerCase();
        Queue q = new Queue(queueName);


        rabbitAdmin.declareExchange(e);
        rabbitAdmin.declareQueue(q);

        rabbitAdmin.declareBinding(BindingBuilder.bind(q).to(e).with(queueName).noargs());


        rabbit.setQueue(queueName);
        //rabbit.setRoutingKey(sb.toString());


        System.out.println("Sending to : " + queueName);

        rabbit.convertAndSend(queueName, order);



        /*SubmitOrder m = (SubmitOrder) rabbit.receiveAndConvert();
        System.out.println(m);*/
    }

    @Override
    @Transactional
    public void cancelOrder(String base, String counter, CancelOrder order) {
        StringBuilder sb = new StringBuilder("orders.cancel.");
        sb.append(base).append(".").append(counter);
    }


}
