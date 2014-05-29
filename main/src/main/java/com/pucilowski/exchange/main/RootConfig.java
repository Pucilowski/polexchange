package com.pucilowski.exchange.main;

import com.pucilowski.exchange.integration.service.client.MatcherClientConfig;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * Created by martin on 13/05/14.
 */
@Configuration
@Import(MatcherClientConfig.class)
@ImportResource("META-INF/spring/applicationContext-jpa.xml")
@PropertySource("META-INF/spring/database.properties")
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

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}