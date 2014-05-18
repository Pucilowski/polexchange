package com.pucilowski.exchange.main;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by martin on 13/05/14.
 */
@Configuration
@ComponentScan(basePackages = {"com.pucilowski.exchange.main", "com.pucilowski.exchange.matcher.integration.client"})
@EnableJpaRepositories
public class RootConfig {

}