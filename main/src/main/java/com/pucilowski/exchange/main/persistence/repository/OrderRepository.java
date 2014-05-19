package com.pucilowski.exchange.main.persistence.repository;

import com.pucilowski.exchange.main.persistence.entity.Order;
import com.pucilowski.exchange.main.persistence.entity.User;
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

    /**
     * user's orders
     */

    @Query("SELECT o FROM Order o " +
            "WHERE o.user = ?1 " +
            "ORDER BY o.opened DESC")
    Collection<Order> getOrders(User user) throws DataAccessException;

    /**
     * opening orders
     */

    @Query("SELECT o FROM Order o " +
            "WHERE o.status = com.pucilowski.exchange.common.enums.OrderStatus.OPENING " +
            "ORDER BY o.opened ASC")
    Collection<Order> getOpening() throws DataAccessException;

    @Query("SELECT o FROM Order o " +
            "WHERE o.status = com.pucilowski.exchange.common.enums.OrderStatus.OPENING " +
            "AND o.market.id = ?1 ORDER BY o.opened ASC")
    ArrayList<Order> getOpening(CurrencyPair id) throws DataAccessException;

    /**
     * open orders
     */

    @Query("SELECT o FROM Order o " +
            "WHERE o.status = com.pucilowski.exchange.common.enums.OrderStatus.OPEN " +
            "ORDER BY o.opened ASC")
    Collection<Order> getOpen() throws DataAccessException;

    @Query("SELECT o FROM Order o JOIN o.market m " +
            "WHERE o.status = com.pucilowski.exchange.common.enums.OrderStatus.OPEN " +
            "AND o.market = m AND m.id = ?1 ORDER BY o.opened DESC")
    Collection<Order> getOpen(CurrencyPair id) throws DataAccessException;

    @Query("SELECT o FROM Order o " +
            "WHERE o.side =  com.pucilowski.exchange.common.enums.OrderSide.BID " +
            "AND o.status =  com.pucilowski.exchange.common.enums.OrderStatus.OPEN " +
            "AND o.market.id = ?1 ORDER BY o.price DESC")
    ArrayList<Order> getOpenBids(CurrencyPair id) throws DataAccessException;

    @Query("SELECT o FROM Order o " +
            "WHERE o.side =  com.pucilowski.exchange.common.enums.OrderSide.ASK " +
            "AND o.status =  com.pucilowski.exchange.common.enums.OrderStatus.OPEN " +
            "AND o.market.id = ?1 ORDER BY o.price ASC")
    ArrayList<Order> getOpenAsks(CurrencyPair id) throws DataAccessException;
}
