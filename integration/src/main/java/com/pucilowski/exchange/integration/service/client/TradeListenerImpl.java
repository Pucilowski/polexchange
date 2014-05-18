package com.pucilowski.exchange.integration.service.client;

import com.pucilowski.exchange.integration.model.out.TradeExecuted;
import org.springframework.stereotype.Service;

/**
 * Created by martin on 18/05/14.
 */

@Service
public class TradeListenerImpl implements TradeListener {
    @Override
    public void tradeExecuted(TradeExecuted trade) {
        System.out.println("Trade executed: " + trade);
    }
}
