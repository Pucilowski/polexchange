package com.pucilowski.exchange.matcher;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: martin
 * Date: 02/12/13
 * Time: 07:30
 * To change this template use File | Settings | File Templates.
 */

public class Run {
    public static void main(String[] args) {
        //book = new MarketMatcher();

        //MarketMatcher unit = new MarketMatcher();
        //ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/matcherContext.xml");
        //Run p = (Run) context.getBean("run");
        ConfigurableApplicationContext context =
                SpringApplication.run(MatcherConfig.class, args);


        //OrderListener listener = context.getBean(OrderListener.class);
        //Matcher matcher = context.getBean(Matcher.class);

    }

}
