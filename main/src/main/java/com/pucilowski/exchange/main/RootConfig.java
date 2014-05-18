package com.pucilowski.exchange.main;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by martin on 13/05/14.
 */
@Configuration
@EnableJpaRepositories
public class RootConfig {

}