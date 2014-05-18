package com.pucilowski.exchange.main.persistence.repository;

import com.pucilowski.exchange.main.persistence.entity.Currency;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by martin on 31/01/14.
 */

public interface CurrencyRepository extends CrudRepository<Currency,String> {

}
