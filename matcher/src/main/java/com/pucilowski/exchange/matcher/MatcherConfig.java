package com.pucilowski.exchange.matcher;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by martin on 18/05/14.
 */

@Configuration
@ImportResource("classpath:META-INF/spring/matcherContext.xml")
//@Import(MatcherServerConfig.class)
@PropertySource("classpath:config/market.properties")
public class MatcherConfig {


    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
