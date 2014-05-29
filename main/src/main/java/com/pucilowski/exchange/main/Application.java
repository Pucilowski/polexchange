package com.pucilowski.exchange.main;


import com.pucilowski.exchange.main.persistence.repository.CurrencyRepository;
import com.pucilowski.exchange.main.persistence.repository.MarketRepository;
import com.pucilowski.exchange.main.util.PopulateSchema;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by martin on 13/05/14.
 */


//@Configuration
//@EnableAutoConfiguration
//@ComponentScan(basePackages = "com.pucilowski.exchange")
//@EnableJpaRepositories(basePackages = "com.pucilowski.exchange.persistence.repository")
public class Application {

    public static void main(String[] args) throws Throwable {
        ConfigurableApplicationContext context =
                SpringApplication.run(ServletInitializer.class, args);

        CurrencyRepository currencyRepository = context.getBean(CurrencyRepository.class);
        MarketRepository marketRepository = context.getBean(MarketRepository.class);
        PopulateSchema.populateCurrencies3(currencyRepository, marketRepository);
    }

}