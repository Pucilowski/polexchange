package com.pucilowski.exchange.main.persistence.repository;

import com.pucilowski.exchange.main.persistence.entity.Market;
import com.pucilowski.exchange.main.persistence.entity.id.CurrencyPair;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

/**
 * Created by martin on 31/01/14.
 */


public interface MarketRepository extends CrudRepository<Market,CurrencyPair> {

    public Collection<Market> findAll() throws DataAccessException;

    public Market findOne(CurrencyPair pair) throws DataAccessException;
}
