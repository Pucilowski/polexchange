package com.pucilowski.exchange.main.service.impl;

import com.pucilowski.exchange.api.response.MarketDTO;
import com.pucilowski.exchange.api.response.MarketOrder;
import com.pucilowski.exchange.api.response.MarketTrade;
import com.pucilowski.exchange.main.persistence.entity.Market;
import com.pucilowski.exchange.main.persistence.entity.Order;
import com.pucilowski.exchange.main.persistence.entity.Trade;
import com.pucilowski.exchange.main.persistence.entity.id.CurrencyPair;
import com.pucilowski.exchange.main.persistence.repository.MarketRepository;
import com.pucilowski.exchange.main.persistence.repository.OrderRepository;
import com.pucilowski.exchange.main.persistence.repository.TradeRepository;
import com.pucilowski.exchange.main.service.MarketService;
import com.pucilowski.exchange.main.util.Converters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Martin on 29/01/14.
 */

//TODO these can be cached
@Service
@Transactional(readOnly = true)
public class MarketServiceImpl implements MarketService {

    @Autowired
    MarketRepository marketRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    TradeRepository tradeRepository;

    @Override
    public Collection<MarketDTO> getMarkets() {
        Collection<com.pucilowski.exchange.main.persistence.entity.Market> markets = marketRepository.findAll();
        return convertMarkets(markets);
    }

    @Override
    public MarketDTO getMarket(String base, String counter) {
        CurrencyPair pair = Converters.toCurrency(base, counter);
        Market market = marketRepository.findOne(pair);
        return convertMarket(market);
    }

    @Override
    public Collection<MarketOrder> getBids(String base, String counter) {
        CurrencyPair pair = Converters.toCurrency(base, counter);
        ArrayList<Order> orders = orderRepository.getOpenBids(pair);
        return convertOrders(orders);
    }

    @Override
    public Collection<MarketOrder> getAsks(String base, String counter) {
        CurrencyPair pair = Converters.toCurrency(base, counter);
        ArrayList<Order> orders = orderRepository.getOpenAsks(pair);
        return convertOrders(orders);
    }

    @Override
    public Collection<MarketTrade> getHistory(String base, String counter) {
        CurrencyPair pair = Converters.toCurrency(base, counter);
        Collection<Trade> trades = tradeRepository.getTrades(pair);
        return convertTrades(trades);
    }

    public static MarketDTO convertMarket(Market market) {
        CurrencyPair pair = market.getId();
        String base = pair.getBase().getCode();
        String counter = pair.getCounter().getCode();
        return new MarketDTO(base, counter);
    }

    public static Collection<MarketDTO> convertMarkets(Collection<Market> markets) {
        List<MarketDTO> results = new LinkedList<>();
        for (Market o : markets)
            results.add(convertMarket(o));
        return results;
    }

    public static Collection<MarketOrder> convertOrders(Collection<Order> orders) {
        List<MarketOrder> results = new LinkedList<>();
        for (Order o : orders)
            results.add(new MarketOrder(o.getPrice(), o.getRemaining()));
        return results;
    }

    public static Collection<MarketTrade> convertTrades(Collection<Trade> trades) {
        List<MarketTrade> results = new LinkedList<>();
        for (Trade t : trades)
            results.add(new MarketTrade(t.getSide(), t.getPrice(), t.getQuantity(), t.getExecuted()));
        return results;
    }


}
