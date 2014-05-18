package com.pucilowski.exchange.main.integration.service;

import com.pucilowski.exchange.main.persistence.entity.Order;
import com.pucilowski.exchange.main.persistence.entity.Trade;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martin on 03/02/14.
 */
public interface MatcherService {
    public ArrayList<Trade> processPendingOrders(String base, String counter) ;
    void processPending(Order pending, List<Order> openOrders);
    Trade match(Order pending, Order open);
}
