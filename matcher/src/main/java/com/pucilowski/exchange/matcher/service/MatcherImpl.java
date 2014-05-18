package com.pucilowski.exchange.matcher.service;

import com.pucilowski.exchange.common.enums.OrderSide;
import com.pucilowski.exchange.integration.service.server.MatcherServer;
import com.pucilowski.exchange.matcher.model.Order;
import com.pucilowski.exchange.matcher.model.BookSide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by martin on 18/05/14.
 */

@Service
public class MatcherImpl implements Matcher {

    final BookSide bids;
    final BookSide asks;

    @Autowired
    MatcherServer matcherServer;

    public MatcherImpl() {
        bids = new BookSide(OrderSide.BID);
        asks = new BookSide(OrderSide.ASK);
    }

    @Override
    public void inputOrder(Order pending) {
        BookSide bookSide = getBook(pending);
        BookSide counterBookSide = getCounterBook(pending);

        if (pending.side == OrderSide.BID && pending.price < bookSide.best) {
            bookSide.push(pending);
            return;
        } else if (pending.side == OrderSide.ASK && pending.price > bookSide.best) {
            bookSide.push(pending);
            return;
        }


        while (pending.remaining > 0) {
            Order o = counterBookSide.peek();
            int maxQ = Math.min(pending.quantity, o.quantity);
        }

        //orderBook.add(pending);
        //boolean bestOffer = orderBook.add(pending)==0;

        //matcherServer.tradeExecuted(null);
    }

    @Override
    public void cancelOrder(long id) {

    }

    public BookSide getBook(Order order) {
        return order.side == OrderSide.BID ? bids : asks;
    }

    public BookSide getCounterBook(Order order) {
        return order.side != OrderSide.BID ? bids : asks;
    }

}
