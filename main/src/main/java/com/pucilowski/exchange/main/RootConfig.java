package com.pucilowski.exchange.main;

import com.pucilowski.exchange.integration.model.out.TradeExecuted;
import com.pucilowski.exchange.integration.service.client.MatcherClientConfig;
import com.pucilowski.exchange.integration.service.client.TradeListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by martin on 13/05/14.
 */
@Configuration
@Import(MatcherClientConfig.class)
@ComponentScan(basePackages = {"com.pucilowski.exchange.main", "com.pucilowski.exchange.integration.client"})
@EnableJpaRepositories
public class RootConfig {

/*    @Bean
    public TradeListener tradeListener() {
        return new TradeListener() {
            @Override
            public void tradeExecuted(TradeExecuted trade) {
                System.out.println("In: " + trade);
            }
        };
    }*/

}