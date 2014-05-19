package com.pucilowski.exchange.main.web.controller.api;

import com.pucilowski.exchange.api.request.CancelOrder;
import com.pucilowski.exchange.api.request.SubmitOrder;
import com.pucilowski.exchange.main.service.MarketService;
import com.pucilowski.exchange.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by martin on 03/02/14.
 */

@Controller
@RequestMapping(value = "/api/")
public class OrderApiController {

    @Autowired
    UserService userService;

    @Autowired
    MarketService marketService;


    @RequestMapping(value = "/order/submit", method = RequestMethod.POST)
    @ResponseBody
    public String submitOrder(@RequestBody SubmitOrder order) {
        userService.submitOrder(order);

        return "order submitted";
    }

    @RequestMapping(value = "/order/cancel", method = RequestMethod.POST)
    @ResponseBody
    public String cancelOrder(@RequestBody CancelOrder order) {

        userService.cancelOrder(order);

        System.out.println("ctrl");
        return "markets/list";
    }

}