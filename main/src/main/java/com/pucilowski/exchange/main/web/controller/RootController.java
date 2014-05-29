package com.pucilowski.exchange.main.web.controller;

import com.pucilowski.exchange.api.response.MarketDTO;
import com.pucilowski.exchange.main.persistence.entity.User;
import com.pucilowski.exchange.main.service.MarketService;
import com.pucilowski.exchange.main.service.UserContext;
import com.pucilowski.exchange.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Collection;

/**
 * Created by martin on 19/05/14.
 */

@Controller
public class RootController {
    @Autowired
    UserContext userContext;

    @Autowired
    MarketService marketService;

    @RequestMapping(value = "/")
    @ResponseBody
    public String viewHome(Principal principal) {
        return "hello, \n" + principal + "\naka:\n" + userContext.getUser();
    }

    @RequestMapping(value = "/markets")
    @ResponseBody
    public Collection<MarketDTO> viewMarkets() {
        return marketService.getMarkets();
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerPage() {
        return "register";
    }

    @Autowired
    UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerAction(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam("email") String email,
                                 @RequestParam("password") String password,
                                 @RequestParam("confirm") String confirm
    ) throws IOException {
        System.out.println(email + ":" + password);
        User u = userService.registerUser(email, password);
        authenticateUserAndSetSession(u, request);
        response.sendRedirect("/");
        return "eyo";
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    private void authenticateUserAndSetSession(User user, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());

        // generate session if one doesn't exist
        request.getSession();
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }

}
