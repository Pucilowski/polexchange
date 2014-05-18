package com.pucilowski.exchange.matcher.integration.service;

import com.pucilowski.exchange.matcher.matching.model.Trade;

/**
 * Created by martin on 13/05/14.
 */
public interface MatchBroadcaster {

    void broadcastTrade(Trade trade);

}
