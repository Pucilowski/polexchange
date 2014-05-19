package com.pucilowski.exchange.main.service;

import com.pucilowski.exchange.api.response.MarketDTO;
import com.pucilowski.exchange.api.response.OrderbookDTO;
import com.pucilowski.exchange.api.response.TradeDTO;

import java.util.Collection;

/**
 * Created by martin on 03/02/14.
 */
public interface MarketService {

    Collection<MarketDTO> getMarkets();

    MarketDTO getMarket(String base, String counter);

    OrderbookDTO getOrderbook(String base, String counter);

    TradeDTO getTrades(String base, String counter);
}

