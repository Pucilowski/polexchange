package com.pucilowski.exchange.main.persistence.repository;

import com.pucilowski.exchange.main.persistence.entity.Trade;
import com.pucilowski.exchange.main.persistence.entity.id.CurrencyPair;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

/**
 * Created by martin on 31/01/14.
 */


public interface TradeRepository extends CrudRepository<Trade, Long> {

    Collection<Trade> findAll() throws DataAccessException;

    //@Query("SELECT t FROM Trade t JOIN t.market m WHERE m.id = ?1 ORDER BY t.time DESC")
    @Query("SELECT t FROM Trade t JOIN t.market m " +
            "WHERE m.id = ?1 ORDER BY t.executed DESC")
    Collection<Trade> getTrades(CurrencyPair id);
}
