package com.pucilowski.exchange.main.service.impl;


import com.pucilowski.exchange.api.user.request.SubmitOrder;
import com.pucilowski.exchange.common.enums.OrderStatus;
import com.pucilowski.exchange.integration.model.in.OrderCancelled;
import com.pucilowski.exchange.integration.model.in.OrderSubmitted;
import com.pucilowski.exchange.integration.service.client.MatcherClient;
import com.pucilowski.exchange.main.persistence.entity.Market;
import com.pucilowski.exchange.main.persistence.entity.Order;
import com.pucilowski.exchange.main.persistence.entity.Trade;
import com.pucilowski.exchange.main.persistence.entity.User;
import com.pucilowski.exchange.main.persistence.entity.id.CurrencyPair;
import com.pucilowski.exchange.main.persistence.repository.OrderRepository;
import com.pucilowski.exchange.main.persistence.repository.TradeRepository;
import com.pucilowski.exchange.main.persistence.repository.UserRepository;
import com.pucilowski.exchange.main.service.UserContext;
import com.pucilowski.exchange.main.service.UserService;
import com.pucilowski.exchange.main.util.Converters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Martin on 29/01/14.
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    TradeRepository tradeRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MatcherClient matcherClient;

    @Autowired
    UserContext userContext;

    @Override
    @Transactional
    public OrderSubmitted submitOrder(UserDetails principal, SubmitOrder dto) {
        String base = dto.getBase();
        String counter = dto.getCounter();
        CurrencyPair pair = Converters.toCurrency(base, counter);

        Market m = new Market();
        m.setId(pair);

        Order order = new Order();
        order.user = userContext.getUser();
        order.market = m;
        order.side = dto.getSide();
        order.price = dto.getPrice();
        order.quantity = dto.getQuantity();
        order.remaining = dto.getQuantity();
        order.status = OrderStatus.OPEN;
        order.opened = new Date();
        orderRepository.save(order);


        OrderSubmitted s = new OrderSubmitted();
        s.id = order.id;
        s.side = order.side;
        s.price = order.price;
        s.quantity = order.quantity;
        matcherClient.submitOrder(base, counter, s);

        return s;
    }

    @Override
    @Transactional
    public void cancelOrder(Principal principal, Long id) {
        Order o = orderRepository.findOne(id);



        o.setStatus(OrderStatus.CANCELLING);

        orderRepository.save(o);

        String base = o.getMarket().getId().getBase().getCode();
        String counter = o.getMarket().getId().getCounter().getCode();

        OrderCancelled oc = new OrderCancelled(id);
        matcherClient.cancelOrder(base, counter, oc);
    }

    @Override
    public Collection<Order> getOrders(Principal principal) {
        return orderRepository.findAll();
    }

    @Override
    public Collection<Trade> getTrades(Principal principal) {
        return tradeRepository.findAll();
    }

    @Autowired
    PasswordEncoder passwordEncoder;

    //@PostConstruct
    public void init() {
        registerUser("ploo", "aaaa");
    }

    @Override
    public User registerUser(String email, String password) {
        User u = new User();

        u.setEmail(email);
        u.setPassword(passwordEncoder.encode(password));
        u.setRegistered(new Date());

        userRepository.save(u);

        return u;
    }


}
