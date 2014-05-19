package com.pucilowski.exchange.matcher.service;

import com.pucilowski.exchange.common.enums.OrderSide;
import com.pucilowski.exchange.integration.model.out.TradeExecuted;
import com.pucilowski.exchange.integration.service.server.MatcherServer;
import com.pucilowski.exchange.matcher.model.BookSide;
import com.pucilowski.exchange.matcher.model.Order;
import com.pucilowski.exchange.matcher.model.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by martin on 18/05/14.
 */

@Service
public class MatcherImpl implements Matcher {

    public final BookSide.Bids bids;
    public final BookSide.Asks asks;

    @Autowired
    public
    MatcherServer matcherServer;

    public MatcherImpl() {
        bids = new BookSide.Bids();
        asks = new BookSide.Asks();
    }

    @Override
    public void inputOrder(Order input) {
        if (input == null) return;

        BookSide book = getBook(input);
        BookSide counterBook = getCounterBook(input);

        if (input.getSide() == OrderSide.BID && (asks.isEmpty() || input.getPrice() < asks.bestAsk())) {
            System.out.println("Inserted into bidside");
            bids.insert(input);
            return;
        } else if (input.getSide() == OrderSide.ASK && (bids.isEmpty() || input.getPrice() > bids.bestBid())) {
            System.out.println("Inserted into askside");
            asks.insert(input);
            return;
        }

        while (input.getRemaining() > 0) {
            Order counter = counterBook.bestOffer(input.getPrice());
            System.out.println("Attempting match against: " + counter);
            if (counter == null) break;

            int tradePrice = counter.getPrice();
            int tradeVolume = Math.min(input.getRemaining(), counter.getRemaining());

            Order bidOrder = input.getSide() == OrderSide.BID ? input : counter;
            Order askOrder = input.getSide() == OrderSide.ASK ? input : counter;
            Trade trade = new Trade(bidOrder, askOrder, tradePrice, tradeVolume);

            input.takeRemaining(tradeVolume);
            counter.takeRemaining(tradeVolume);

            if (counter.getRemaining() == 0) {
                counterBook.removeBestOrder();
            }

            executeTrade(trade);
        }

        if (input.getRemaining() > 0) {
            book.insert(input);
        } else {
            closeOrder(input);
        }

        //orderBook.add(input);
        //boolean bestOffer = orderBook.add(input)==0;

        //matcherServer.tradeExecuted(null);
    }


    public void executeTrade(Trade trade) {
        TradeExecuted t = new TradeExecuted();

        t.setBidOrderId(trade.bidOrder.getId());
        t.setAskOrderId(trade.askOrder.getId());
        t.setPrice(trade.price);
        t.setQuantity(trade.quantity);

        //System.out.println("Out: " + trade);
        matcherServer.tradeExecuted(t);
    }

    public void closeOrder(Order order) {

    }

    @Override
    public void cancelOrder(long id) {
        System.out.println("Cancelling order: " + id);
    }

    public BookSide getBook(Order order) {
        return order.side == OrderSide.BID ? bids : asks;
    }

    public BookSide getCounterBook(Order order) {
        return order.side != OrderSide.BID ? bids : asks;
    }

}
