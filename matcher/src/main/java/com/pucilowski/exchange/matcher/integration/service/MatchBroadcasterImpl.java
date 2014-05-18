package com.pucilowski.exchange.matcher.integration.service;

import com.pucilowski.exchange.matcher.matching.model.Trade;
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
    RabbitTemplate rabbit;

    @Override
    public void broadcastTrade(Trade trade) {

        //rabbit.send();

    }
}
