package com.pucilowski.exchange.main.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by martin on 03/02/14.
 */

@Controller
public class RootController {
    @RequestMapping(value="/")
    public String home() {
        return "home";
    }
}
