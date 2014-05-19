package com.pucilowski.exchange.main.web.controller;

import com.pucilowski.exchange.main.service.MarketService;
import com.pucilowski.exchange.main.service.UserService;
import com.pucilowski.exchange.api.response.MarketDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

/**
 * Created by martin on 03/02/14.
 */

@Controller
public class MarketController {

    @Autowired
    MarketService marketService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/markets")
    public String getMarkets(Model model) {
        Collection<MarketDTO> markets = marketService.getMarkets();

        model.addAttribute("market", markets);

        return "markets/list";
    }

    @RequestMapping(value = "/markets/{base}/{counter}")
    public String viewMarket(@PathVariable String base, @PathVariable String counter, Model model) {
        base = base.toUpperCase();
        counter = counter.toUpperCase();

        MarketDTO market = marketService.getMarket(base, counter);

        model.addAttribute("market", market);

        return "markets/view";
    }

}
