package com.pucilowski.exchange.matcher;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: martin
 * Date: 02/12/13
 * Time: 07:30
 * To change this template use File | Settings | File Templates.
 */

@Component
public class Run {



    public static void main(String[] args) {
        //book = new MarketMatcher();

        //MarketMatcher unit = new MarketMatcher();
        //ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/matcherContext.xml");
        //Run p = (Run) context.getBean("run");
        ConfigurableApplicationContext context =
                SpringApplication.run(MatcherConfig.class, args);





    }

}
