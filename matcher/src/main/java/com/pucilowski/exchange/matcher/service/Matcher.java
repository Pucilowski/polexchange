package com.pucilowski.exchange.matcher.service;

import com.pucilowski.exchange.matcher.model.Order;

/**
 * Created by martin on 18/05/14.
 */
public interface Matcher {
    void inputOrder(Order pending);

    void cancelOrder(long id);
}
