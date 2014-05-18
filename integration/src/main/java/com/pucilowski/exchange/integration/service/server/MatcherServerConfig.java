package com.pucilowski.exchange.integration.service.server;

import com.pucilowski.exchange.integration.service.MatcherSharedConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by martin on 18/05/14.
 */

@Configuration
@ComponentScan(basePackageClasses = ServerMarker.class)
@Import(MatcherSharedConfig.class)
@ImportResource("classpath:spring/rabbit-server.xml")
public class MatcherServerConfig {



}
