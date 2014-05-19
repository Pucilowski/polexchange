package com.pucilowski.exchange.matcher;

import com.pucilowski.exchange.integration.service.server.MatcherServerConfig;
import com.pucilowski.exchange.integration.service.server.OrderListener;
import com.pucilowski.exchange.matcher.service.OrderListenerImpl;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by martin on 18/05/14.
 */

@Configuration
@ComponentScan(basePackages = "com.pucilowski.exchange.matcher.service")
/*@ImportResource("classpath:META-INF/spring/matcherContext.xml")*/
@Import(MatcherServerConfig.class)
@PropertySource("classpath:config/market.properties")
public class MatcherConfig {


    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

/*    @Bean
    public OrderListener orderListener() {
        return new OrderListenerImpl();
    }*/
}
