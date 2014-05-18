package com.pucilowski.exchange.matcher.integration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by martin on 18/05/14.
 */

@Configuration
@ComponentScan(basePackages = {"com.pucilowski.exchange.matcher.integration.client"})
@ImportResource("classpath:META-INF/spring/applicationContext-rabbit.xml")
public class MatcherClientConfig {
}
