package com.pucilowski.exchange.main;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by martin on 18/05/14.
 */

@Configuration
@ImportResource("classpath:META-INF/spring/applicationContext-rabbit.xml")
public class RabbitConfig {
}
