package com.pucilowski.exchange.integration.service.client;

import com.pucilowski.exchange.integration.model.out.TradeExecuted;

/**
 * Created by martin on 18/05/14.
 */
public interface TradeListener {
    public void tradeExecuted(TradeExecuted trade);
}
