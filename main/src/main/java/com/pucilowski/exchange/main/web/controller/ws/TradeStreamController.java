package com.pucilowski.exchange.main.web.controller.ws;

import com.pucilowski.exchange.main.persistence.entity.Trade;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Created by martin on 19/05/14.
 */
@Controller
public class TradeStreamController {

    //@MessageMapping("/hello")

    @SendTo("/orders/submitted")
    public Trade publishOpenOrder(Trade trade) {
        return trade;
    }

    @SendTo("/orders/cancelled")
    public Trade publishCancelledOrder(Trade trade) {
        return trade;
    }

    @SendTo("/trades")
    public Trade publishExecutedTrade(Trade trade) {
        return trade;
    }


}
