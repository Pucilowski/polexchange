package com.pucilowski.exchange.integration.service.server;

import com.pucilowski.exchange.integration.model.in.OrderSubmitted;
import com.pucilowski.exchange.integration.model.out.TradeExecuted;
import com.pucilowski.exchange.integration.model.in.OrderCancelled;

/**
 * Created by martin on 13/05/14.
 */
public interface MatcherServer {
    void tradeExecuted(TradeExecuted trade);

    void orderSubmitted(OrderSubmitted order);

    void orderCancelled(OrderCancelled order);
}
