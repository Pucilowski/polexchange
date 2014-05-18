package com.pucilowski.exchange.matcher.integration.server;

import com.pucilowski.exchange.matcher.integration.model.in.OrderCancelled;
import com.pucilowski.exchange.matcher.integration.model.in.OrderSubmitted;
import com.pucilowski.exchange.matcher.matching.model.Trade;

/**
 * Created by martin on 13/05/14.
 */
public interface MatchBroadcaster {

    void broadcastTrade(Trade trade);

    void orderSubmitted(OrderSubmitted order);

    void orderCancelled(OrderCancelled order);

}
