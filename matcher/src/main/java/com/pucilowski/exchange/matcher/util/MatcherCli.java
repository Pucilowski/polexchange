package com.pucilowski.exchange.matcher.util;

import com.pucilowski.exchange.common.enums.OrderSide;
import com.pucilowski.exchange.integration.model.out.TradeExecuted;
import com.pucilowski.exchange.integration.service.server.MatcherServer;
import com.pucilowski.exchange.matcher.model.Order;
import com.pucilowski.exchange.matcher.service.MatcherImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by martin on 19/05/14.
 */
public class MatcherCli {

    MatcherImpl matcher;

    public MatcherCli() {

        matcher = new MatcherImpl();
        matcher.matcherServer = new MatcherServerPrinter();

/*        submitOrder(OrderSide.BID, 70, 230);
        submitOrder(OrderSide.BID, 80, 220);
        submitOrder(OrderSide.BID, 90, 50);
        submitOrder(OrderSide.BID, 90, 100);

        submitOrder(OrderSide.ASK, 110, 100);
        submitOrder(OrderSide.ASK, 120, 250);
        submitOrder(OrderSide.ASK, 120, 150);
        submitOrder(OrderSide.ASK, 130, 200);*/

        printBook();

        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                OrderSide side = parts[0].equalsIgnoreCase("bid") ? OrderSide.BID : OrderSide.ASK;

                submitOrder(side, Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                printBook();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    Random r = new Random();

    public void submitOrder(OrderSide side, int price, int quantity) {
        long id = r.nextInt(100);
        Order order = new Order(id, side, price, quantity);

        System.out.println("In: " + order);
        matcher.inputOrder(order);
    }

    public static void main(String[] args) {
        new MatcherCli();
    }


    public class MatcherServerPrinter implements MatcherServer {
        @Override
        public void tradeExecuted(TradeExecuted trade) {
            System.out.println("Out: " + trade);
        }
    }


    public void printBook() {
        Map<Integer, List<Integer>> bidBook = matcher.bids.getBook();
        Map<Integer, List<Integer>> askBook = matcher.asks.getBook();

        Iterator<Map.Entry<Integer, List<Integer>>> bidIt = bidBook.entrySet().iterator();
        Iterator<Map.Entry<Integer, List<Integer>>> askIt = askBook.entrySet().iterator();

        System.out.println("qty\t\t bid | ask\t\tqty");
        while (bidIt.hasNext() || askIt.hasNext()) {
            System.out.println("-------------|--------------");

            Map.Entry<Integer, List<Integer>> bidEntry = null;
            Map.Entry<Integer, List<Integer>> askEntry = null;

            if (bidIt.hasNext()) {
                bidEntry = bidIt.next();
            }
            if (askIt.hasNext()) {
                askEntry = askIt.next();
            }

            String farA = bidEntry != null ? String.format("%04d", bidEntry.getKey()) : "    ";
            String farB = askEntry != null ? String.format("%04d", askEntry.getKey()) : "    ";

            //System.out.println("\t\t" + "-----" + "-|-" + "-----" + "\t\t");
            //System.out.println("\t\t" + farA + " | " + farB + "\t\t");
            //System.out.println("\t\t" + "     " + " | " + "     " + "\t\t");


            Iterator<Integer> bidVolumes = null;
            Iterator<Integer> askVolumes = null;

            if (bidEntry != null)
                bidVolumes = bidEntry.getValue().iterator();

            if (askEntry != null)
                askVolumes = askEntry.getValue().iterator();

            while (true) {

                Integer bidVolume = null;
                Integer askVolume = null;

                if (bidVolumes != null && bidVolumes.hasNext())
                    bidVolume = bidVolumes.next();

                if (askVolumes != null && askVolumes.hasNext())
                    askVolume = askVolumes.next();

                if (askVolume == null && bidVolume == null) break;


                String a = bidVolume != null ? String.format("%04d", bidVolume) : "    ";
                String b = askVolume != null ? String.format("%04d", askVolume) : "    ";
                System.out.println(a + "\t" + farA + " | " + farB + "\t\t" + b);

                farA = farA.replaceAll(".", " ");
                farB = farB.replaceAll(".", " ");
            }

        }


        //System.out.println("Best bid: " + bids.bestBid() + " Best ask : " + asks.bestAsk());
        //bids.dumpString();
        //asks.dumpString();
    }


}

