package com.pucilowski.exchange.main;


import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created by martin on 13/05/14.
 */


//@Configuration
//@EnableAutoConfiguration
//@ComponentScan(basePackages = "com.pucilowski.exchange")
//@EnableJpaRepositories(basePackages = "com.pucilowski.exchange.persistence.repository")
public class Application {

    public static void main(String[] args) throws Throwable {
        ConfigurableApplicationContext context =
                SpringApplication.run(ServletInitializer.class, args);
    }

}