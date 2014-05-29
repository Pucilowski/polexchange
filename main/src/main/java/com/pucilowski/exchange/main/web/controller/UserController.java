package com.pucilowski.exchange.main.web.controller;

import com.pucilowski.exchange.main.service.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

/**
 * Created by martin on 19/05/14.
 */

@Controller
@RequestMapping(value = "/user")

public class UserController {

    @Autowired
    UserContext userContext;

    @RequestMapping(value = "/")
    @ResponseBody
    public String root(Principal principal) {
        return "hello, " + principal +" aka " + userContext.getUser();
    }


}
