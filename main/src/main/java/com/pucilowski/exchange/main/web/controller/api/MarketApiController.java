package com.pucilowski.exchange.main.web.controller.api;

import com.pucilowski.exchange.api.response.MarketDTO;
import com.pucilowski.exchange.api.response.MarketOrder;
import com.pucilowski.exchange.api.response.MarketTrade;
import com.pucilowski.exchange.main.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created by martin on 03/02/14.
 */

@RestController
@RequestMapping(value = "/api/markets")
public class MarketApiController {

    @Autowired
    MarketService marketService;

    @RequestMapping(value = "/")
    public Collection<MarketDTO> getMarkets() {
        return marketService.getMarkets();
    }

    @RequestMapping(value = "/{base}/{counter}/")
    public MarketDTO getMarket(@PathVariable String base, @PathVariable String counter) {
        base = base.toUpperCase();
        counter = counter.toUpperCase();

        return marketService.getMarket(base,counter);
    }

    @RequestMapping(value = "/{base}/{counter}/bids/")
    public Collection<MarketOrder> getBids(@PathVariable String base, @PathVariable String counter) {
        base = base.toUpperCase();
        counter = counter.toUpperCase();

        return marketService.getBids(base, counter);
    }

    @RequestMapping(value = "/{base}/{counter}/asks/")
    public Collection<MarketOrder> getAsks(@PathVariable String base, @PathVariable String counter) {
        base = base.toUpperCase();
        counter = counter.toUpperCase();

        return marketService.getAsks(base, counter);
    }

    @RequestMapping(value = "/{base}/{counter}/history/")
    public Collection<MarketTrade> getHistory(@PathVariable String base, @PathVariable String counter) {
        base = base.toUpperCase();
        counter = counter.toUpperCase();

        return marketService.getHistory(base, counter);
    }
}
