package com.pucilowski.exchange.main.service;

import com.pucilowski.exchange.main.web.api.request.CancelOrder;
import com.pucilowski.exchange.main.web.api.request.SubmitOrder;
import com.pucilowski.exchange.main.persistence.entity.Order;
import com.pucilowski.exchange.main.persistence.entity.Trade;

import java.util.Collection;

/**
 * Created by martin on 03/02/14.
 */
public interface UserService {

    public void submitOrder(SubmitOrder dto);

    void cancelOrder(CancelOrder dto);

    public Collection<Order> getOrders();

    public Collection<Trade> getTrades();
}
