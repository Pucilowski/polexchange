package com.pucilowski.exchange.integration.service.client;

import com.pucilowski.exchange.integration.model.in.OrderCancelled;
import com.pucilowski.exchange.integration.model.in.OrderSubmitted;
import com.pucilowski.exchange.integration.model.out.TradeExecuted;

/**
 * Created by martin on 03/02/14.
 */

public interface MatcherClient {
    void submitOrder(String base, String counter, OrderSubmitted order);

    void cancelOrder(String base, String counter, OrderCancelled order);

    void tradeExecuted(TradeExecuted trade);
}
