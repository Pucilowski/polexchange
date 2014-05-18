package com.pucilowski.exchange.matcher.integration.request;

import com.pucilowski.exchange.common.enums.OrderSide;

/**
 * Created by martin on 13/05/14.
 */
public class OrderSubmitted {

    public long id;
    public OrderSide side;

    public int price;
    public int quantity;

}
