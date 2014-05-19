package com.pucilowski.exchange.main.service;

import com.pucilowski.exchange.integration.model.out.TradeExecuted;
import com.pucilowski.exchange.integration.service.client.TradeListener;
import com.pucilowski.exchange.main.persistence.entity.Order;
import com.pucilowski.exchange.main.persistence.entity.Trade;
import com.pucilowski.exchange.main.persistence.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by martin on 19/05/14.
 */

@Component("tradeListener")
public class TradeListenerImpl implements TradeListener {

    @Autowired
    TradeRepository tradeRepository;

    @Autowired
    UserService userService;

    @Override
    public void tradeExecuted(TradeExecuted trade) {
        System.out.println("In: " + trade);

        Trade t = new Trade();
        t.setBidOrder(new Order(trade.getBidOrderId()));
        t.setAskOrder(new Order(trade.getAskOrderId()));
        t.setPrice(trade.getPrice());
        t.setQuantity(trade.getQuantity());

        tradeRepository.save(t);
    }
}
