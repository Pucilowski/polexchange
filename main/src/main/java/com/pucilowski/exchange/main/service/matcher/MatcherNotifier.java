package com.pucilowski.exchange.main.service.matcher;


import com.pucilowski.exchange.main.web.api.request.CancelOrder;
import com.pucilowski.exchange.main.web.api.request.SubmitOrder;

/**
 * Created by martin on 03/02/14.
 */

public interface MatcherNotifier {
    public void submitOrder(String base, String counter, SubmitOrder order);

    public void cancelOrder(String base, String counter, CancelOrder order);
}
