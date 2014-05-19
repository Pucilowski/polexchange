package com.pucilowski.exchange.main.util;

import com.pucilowski.exchange.main.ServletInitializer;
import com.pucilowski.exchange.main.persistence.entity.Currency;
import com.pucilowski.exchange.main.persistence.entity.Market;
import com.pucilowski.exchange.main.persistence.entity.id.CurrencyPair;
import com.pucilowski.exchange.main.persistence.repository.CurrencyRepository;
import com.pucilowski.exchange.main.persistence.repository.MarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by martin on 04/02/14.
 */

@Component
public class PopulateSchema {

    @Autowired
    protected CurrencyRepository currencyRepository;

    @Autowired
    protected MarketRepository marketRepository;


    public void populateCurrencies() {
        Currency btc = new Currency("BTC", "Bitcoin");
        Currency ltc = new Currency("LTC", "Litecoin");
        Currency ppc = new Currency("PPC", "Peercoin");
        Currency doge = new Currency("DOGE", "Dogecoin");
        currencyRepository.save(new ArrayList<>(Arrays.asList(btc, ltc, ppc, doge)));

        Market ltcBtc = new Market(new CurrencyPair(ltc, btc));
        Market ppcBtc = new Market(new CurrencyPair(ppc, btc));
        Market dogeBtc = new Market(new CurrencyPair(doge, btc));
        marketRepository.save(new ArrayList<>(Arrays.asList(ltcBtc, ppcBtc, dogeBtc)));

    }

    public static void main(String[] args) {

        ConfigurableApplicationContext context =
                SpringApplication.run(ServletInitializer.class, args);

        PopulateSchema p = (PopulateSchema) context.getBean("populateSchema");

        p.populateCurrencies();
    }

}

