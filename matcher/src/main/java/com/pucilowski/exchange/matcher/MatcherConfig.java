package com.pucilowski.exchange.matcher;

import com.pucilowski.exchange.integration.service.server.MatcherServerConfig;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by martin on 18/05/14.
 */

@Configuration
@ComponentScan(basePackages = "com.pucilowski.exchange.matcher.service")
/*@ImportResource("classpath:META-INF/spring/matcherContext.xml")*/
@Import(MatcherServerConfig.class)
@PropertySource("classpath:config/matcher.properties")
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
