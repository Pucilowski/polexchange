package com.pucilowski.exchange.main.persistence.repository;

import com.pucilowski.exchange.main.persistence.entity.Order;
import com.pucilowski.exchange.main.persistence.entity.id.CurrencyPair;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by martin on 31/01/14.
 */


public interface OrderRepository extends CrudRepository<Order, Long> {

    Collection<Order> findAll() throws DataAccessException;

    @Query("SELECT o FROM Order o JOIN o.market m " +
            "WHERE o.status = com.pucilowski.exchange.common.enums. OrderStatus.OPEN " +
            "AND o.market = m AND m.id = ?1 ORDER BY o.price DESC")
    Collection<Order> getOpenOrders(CurrencyPair id) throws DataAccessException;


    @Query("SELECT o FROM Order o " +
            "WHERE o.status = com.pucilowski.exchange.common.enums. OrderStatus.PENDING " +
            "ORDER BY o.submitted ASC")
    Collection<Order> getPendingOrders() throws DataAccessException;

    @Query("SELECT o FROM Order o " +
            "WHERE o.status = com.pucilowski.exchange.common.enums.OrderStatus.OPEN " +
            "ORDER BY o.submitted ASC")
    Collection<Order> getOpenOrders() throws DataAccessException;


    // pair specific
    @Query("SELECT o FROM Order o " +
            "WHERE o.status = com.pucilowski.exchange.common.enums.OrderStatus.PENDING " +
            "AND o.market.id = ?1 " +
            "ORDER BY o.submitted ASC")
    ArrayList<Order> getPendingOrders(CurrencyPair id) throws DataAccessException;

    @Query("SELECT o FROM Order o " +
            "WHERE o.side =  com.pucilowski.exchange.common.enums.OrderSide.BID " +
            "AND o.status =  com.pucilowski.exchange.common.enums.OrderStatus.OPEN " +
            "AND o.market.id = ?1 " +
            "ORDER BY o.price DESC")
    ArrayList<Order> getOpenBidOrders(CurrencyPair id) throws DataAccessException;

    @Query("SELECT o FROM Order o " +
            "WHERE o.side =  com.pucilowski.exchange.common.enums.OrderSide.ASK " +
            "AND o.status =  com.pucilowski.exchange.common.enums.OrderStatus.OPEN " +
            "AND o.market.id = ?1 " +
            "ORDER BY o.price ASC")
    ArrayList<Order> getOpenAskOrders(CurrencyPair id) throws DataAccessException;
}
