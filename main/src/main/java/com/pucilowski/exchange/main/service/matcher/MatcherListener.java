package com.pucilowski.exchange.main.service.matcher;


import com.pucilowski.exchange.main.web.api.request.SubmitOrder;

/**
 * Created by Martin on 29/01/14.
 */


public interface MatcherListener {
    public void onOrderSubmitted(SubmitOrder order);
}
