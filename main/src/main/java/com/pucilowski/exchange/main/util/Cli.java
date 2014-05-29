package com.pucilowski.exchange.main.util;

import com.pucilowski.exchange.api.user.request.SubmitOrder;
import com.pucilowski.exchange.common.enums.OrderSide;
import com.pucilowski.exchange.main.ServletInitializer;
import com.pucilowski.exchange.main.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Principal;
import java.util.Collections;

/**
 * Created by martin on 19/05/14.
 */
//@Component
//@Service("cliInput")
public class Cli {

    UserService userService;


    public Cli() {
        userService = context.getBean(UserService.class);

        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split(" ");
                    OrderSide side = parts[0].equalsIgnoreCase("bid") ? OrderSide.BID : OrderSide.ASK;

                    User u = new User("A", "b", Collections.<GrantedAuthority>emptyList());
                    submitOrder(u, side, Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static final String BASE = "gbp";
    public static final String COUNTER = "usd";

    public void submitOrder(UserDetails principal, OrderSide side, int price, int quantity) {
        SubmitOrder order = new SubmitOrder(BASE, COUNTER, side, price, quantity);

        System.out.println("Submitting: " + order);
        userService.submitOrder(principal, order);
    }

    static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(ServletInitializer.class, args);

        new Cli();
    }
    //matcher = new MatcherImpl();
    //matcher.matcherServer = new MatcherServerPrinter();

/*        submitOrder(OrderSide.BID, 70, 230);
        submitOrder(OrderSide.BID, 80, 220);
        submitOrder(OrderSide.BID, 90, 50);
        submitOrder(OrderSide.BID, 90, 100);

        submitOrder(OrderSide.ASK, 110, 100);
        submitOrder(OrderSide.ASK, 120, 250);
        submitOrder(OrderSide.ASK, 120, 150);
        submitOrder(OrderSide.ASK, 130, 200);*/

}

