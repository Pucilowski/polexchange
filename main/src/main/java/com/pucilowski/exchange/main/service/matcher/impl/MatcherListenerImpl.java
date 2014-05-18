package com.pucilowski.exchange.main.service.matcher.impl;


import com.pucilowski.exchange.main.service.matcher.MatcherListener;
import com.pucilowski.exchange.main.web.api.request.SubmitOrder;
import org.springframework.stereotype.Service;

/**
 * Created by Martin on 29/01/14.
 */

@Service
public class MatcherListenerImpl implements MatcherListener {
    public void onOrderSubmitted(SubmitOrder order) {
        System.out.println("New Order: " + order);
    }
}
