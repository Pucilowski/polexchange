package com.pucilowski.exchange.main.util;

import com.pucilowski.exchange.main.persistence.entity.Currency;
import com.pucilowski.exchange.main.persistence.entity.id.CurrencyPair;

/**
 * Created by martin on 19/05/14.
 */
public class Converters {

    public static CurrencyPair toCurrency(String base, String counter) {
        base = base.toUpperCase();
        counter = counter.toUpperCase();

        return new CurrencyPair(new Currency(base), new Currency(counter));
    }

}
