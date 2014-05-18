package com.pucilowski.exchange.matcher.matching.service;

import com.pucilowski.exchange.matcher.matching.model.Order;

/**
 * Created by martin on 18/05/14.
 */
public interface Matcher {
    void inputOrder(Order pending);

    void cancelOrder(long id);
}
