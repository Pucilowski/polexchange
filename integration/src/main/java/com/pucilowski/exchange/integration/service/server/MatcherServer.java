package com.pucilowski.exchange.integration.service.server;

import com.pucilowski.exchange.integration.model.out.TradeExecuted;

/**
 * Created by martin on 13/05/14.
 */
public interface MatcherServer {
    void tradeExecuted(TradeExecuted trade);
}
