package com.pucilowski.exchange.main.web.controller.api;

import com.pucilowski.exchange.api.response.MarketDTO;
import com.pucilowski.exchange.api.response.OrderbookDTO;
import com.pucilowski.exchange.api.response.TradeDTO;
import com.pucilowski.exchange.main.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * Created by martin on 03/02/14.
 */

@Controller
@RequestMapping(value = "/api/markets")
public class MarketApiController {

    @Autowired
    MarketService marketService;

    @RequestMapping(value = "/")
    @ResponseBody
    public Collection<MarketDTO> getMarkets() {
        return marketService.getMarkets();
    }

    @RequestMapping(value = "/{base}/{counter}/")
    @ResponseBody
    public MarketDTO getMarket(@PathVariable String base, @PathVariable String counter) {
        base = base.toUpperCase();
        counter = counter.toUpperCase();

        return marketService.getMarket(base,counter);
    }

    @RequestMapping(value = "/{base}/{counter}/orderbook/")
    @ResponseBody
    public OrderbookDTO getOrderbook(@PathVariable String base, @PathVariable String counter) {
        base = base.toUpperCase();
        counter = counter.toUpperCase();

        return marketService.getOrderbook(base,counter);
    }

    @RequestMapping(value = "/{base}/{counter}/trades/")
    @ResponseBody
    public TradeDTO getTrades(@PathVariable String base, @PathVariable String counter) {
        base = base.toUpperCase();
        counter = counter.toUpperCase();

        return marketService.getTrades(base,counter);
    }
}
