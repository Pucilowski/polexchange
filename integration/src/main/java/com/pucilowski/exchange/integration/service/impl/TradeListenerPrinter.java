package com.pucilowski.exchange.integration.service.impl;

import com.pucilowski.exchange.integration.model.out.TradeExecuted;
import com.pucilowski.exchange.integration.service.client.TradeListener;
import org.springframework.stereotype.Service;

/**
 * Created by martin on 18/05/14.
 */

@Service
public class TradeListenerPrinter implements TradeListener {
    @Override
    public void tradeExecuted(TradeExecuted trade) {
        System.out.println("Trade executed: " + trade);
    }
}
