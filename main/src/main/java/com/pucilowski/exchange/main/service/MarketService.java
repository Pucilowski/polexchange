package com.pucilowski.exchange.main.service;

import com.pucilowski.exchange.main.web.api.response.world.MarketDTO;
import com.pucilowski.exchange.main.web.api.response.world.OrderbookDTO;
import com.pucilowski.exchange.main.web.api.response.world.TradeDTO;

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

