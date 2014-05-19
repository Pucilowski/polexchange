package com.pucilowski.exchange.main.service;

import com.pucilowski.exchange.api.user.request.SubmitOrder;
import com.pucilowski.exchange.integration.model.in.OrderSubmitted;
import com.pucilowski.exchange.main.persistence.entity.Order;
import com.pucilowski.exchange.main.persistence.entity.Trade;

import java.util.Collection;

/**
 * Created by martin on 03/02/14.
 */
public interface UserService {

    Collection<Order> getOrders();

    OrderSubmitted submitOrder(SubmitOrder dto);

    void cancelOrder(Long id);

    Collection<Trade> getTrades();
}
