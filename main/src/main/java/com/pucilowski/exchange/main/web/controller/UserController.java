package com.pucilowski.exchange.main.web.controller;

import com.pucilowski.exchange.main.service.UserService;
import com.pucilowski.exchange.main.web.model.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by martin on 03/02/14.
 */

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    //@RequestMapping(value = "/")
    @ResponseBody
    public String home() {
        System.out.println("ctrl");
        return "user/account";
    }

    @RequestMapping(value = "/balance")
    public String listBalances() {
        return "user/balanceList";
    }

    @RequestMapping(value = "/balance/{id}")
    public String getWallet(@RequestParam String id) {
        return "user/balance";
    }


    @RequestMapping(value = "/login")
    public String login() {

        return "login";
    }

    @RequestMapping(value = "/logout")
    public String logout() {
        return "logout";
    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerForm() {
        System.out.println("ayo");
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String doRegister(@RequestBody RegistrationForm form) {
        return "home";
    }
}
