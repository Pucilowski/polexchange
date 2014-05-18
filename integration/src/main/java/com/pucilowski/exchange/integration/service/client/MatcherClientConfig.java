package com.pucilowski.exchange.integration.service.client;

import com.pucilowski.exchange.integration.service.MatcherSharedConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by martin on 18/05/14.
 */

@Configuration
@ComponentScan(basePackageClasses = ClientMarker.class)
@Import(MatcherSharedConfig.class)
@ImportResource("classpath:spring/rabbit-client.xml")
public class MatcherClientConfig {
}
