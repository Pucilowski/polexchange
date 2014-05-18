package com.pucilowski.exchange.matcher.integration.client;

import com.pucilowski.exchange.matcher.integration.in.OrderCancelled;
import com.pucilowski.exchange.matcher.integration.in.OrderSubmitted;
import com.pucilowski.exchange.matcher.integration.out.TradeExecuted;

/**
 * Created by martin on 03/02/14.
 */

public interface MatcherMessaging {
    public void submitOrder(String base, String counter, OrderSubmitted order);

    public void cancelOrder(String base, String counter, OrderCancelled order);

    void tradeExecuted(TradeExecuted trade);
}
