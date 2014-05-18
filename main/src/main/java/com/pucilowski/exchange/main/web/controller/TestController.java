package com.pucilowski.exchange.main.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by martin on 03/02/14.
 */

@Controller
@RequestMapping(value = "/test")
public class TestController {

    @RequestMapping
    @ResponseBody
    public String home() {
        return "home";
    }
}
