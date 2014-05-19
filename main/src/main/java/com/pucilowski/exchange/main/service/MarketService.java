package com.pucilowski.exchange.main.service;

import com.pucilowski.exchange.api.response.MarketDTO;
import com.pucilowski.exchange.api.response.MarketOrder;
import com.pucilowski.exchange.api.response.MarketTrade;

import java.util.Collection;

/**
 * Created by martin on 03/02/14.
 */
public interface MarketService {

    Collection<MarketDTO> getMarkets();

    MarketDTO getMarket(String base, String counter);

    Collection<MarketOrder> getBids(String base, String counter);

    Collection<MarketOrder> getAsks(String base, String counter);

    Collection<MarketTrade> getHistory(String base, String counter);
}

