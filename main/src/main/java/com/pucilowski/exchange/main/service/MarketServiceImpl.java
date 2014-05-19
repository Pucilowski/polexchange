package com.pucilowski.exchange.main.service;

import com.pucilowski.exchange.api.response.MarketDTO;
import com.pucilowski.exchange.api.response.OrderDTO;
import com.pucilowski.exchange.api.response.OrderbookDTO;
import com.pucilowski.exchange.api.response.TradeDTO;
import com.pucilowski.exchange.main.persistence.entity.Currency;
import com.pucilowski.exchange.main.persistence.entity.Market;
import com.pucilowski.exchange.main.persistence.entity.Order;
import com.pucilowski.exchange.main.persistence.entity.Trade;
import com.pucilowski.exchange.main.persistence.entity.id.CurrencyPair;
import com.pucilowski.exchange.common.enums.OrderSide;
import com.pucilowski.exchange.main.persistence.repository.MarketRepository;
import com.pucilowski.exchange.main.persistence.repository.OrderRepository;
import com.pucilowski.exchange.main.persistence.repository.TradeRepository;
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
        Collection<Market> markets = marketRepository.findAll();

        List<MarketDTO> dtos = new ArrayList<>();

        for (Market m : markets) {
            dtos.add(toDto(m));
        }

        return dtos;
    }

    @Override
    public MarketDTO getMarket(String base, String counter) {
        Market market = marketRepository.findOne(new CurrencyPair(new Currency(base), new Currency(counter)));

        return toDto(market);
    }

    @Override
    public OrderbookDTO getOrderbook(String base, String counter) {
        CurrencyPair id = new CurrencyPair(new Currency(base), new Currency(counter));

        ArrayList<Order> bids = orderRepository.getOpenBidOrders(id);
        ArrayList<Order> asks = orderRepository.getOpenAskOrders(id);

        List<OrderDTO> bidDtos = new LinkedList<>();
        List<OrderDTO> askDtos = new LinkedList<>();

        for (Order o : bids) {
            OrderDTO dto = new OrderDTO();
            dto.setPrice(o.price);
            dto.setQuantity(o.quantity);
            bidDtos.add(dto);
        }

        for (Order o : asks) {
            OrderDTO dto = new OrderDTO();
            dto.setPrice(o.price);
            dto.setQuantity(o.quantity);
            askDtos.add(dto);
        }

        return new OrderbookDTO(bidDtos, askDtos);
    }

    //@Override
    public OrderbookDTO getOrderbook2(String base, String counter) {
        CurrencyPair id = new CurrencyPair(new Currency(base), new Currency(counter));
        Collection<Order> openOrders = orderRepository.getOpenOrders(id);

        ArrayList<OrderDTO> bids = new ArrayList<>();
        ArrayList<OrderDTO> asks = new ArrayList<>();

        for (Order o : openOrders) {
            OrderDTO dto = new OrderDTO();
            dto.setPrice(o.price);
            dto.setQuantity(o.quantity);

            if (o.side == OrderSide.BID) {
                bids.add(dto);
            } else {
                asks.add(dto);
            }
        }

        return new OrderbookDTO(bids, asks);
    }

    @Override
    public TradeDTO getTrades(String base, String counter) {
        CurrencyPair id = new CurrencyPair(new Currency(base), new Currency(counter));
        Collection<Trade> trades = tradeRepository.getTradeByMarket(id);

        return null;
    }


    public static MarketDTO toDto(Market market) {
        MarketDTO dto = new MarketDTO();

        dto.setBase(market.getId().getBase().getCode());
        dto.setCounter(market.getId().getCounter().getCode());

        return dto;
    }
}
