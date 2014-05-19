package com.pucilowski.exchange.api.response;

import com.pucilowski.exchange.common.enums.OrderSide;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by martin on 20/01/14.
 */
public class SubmittedOrder {

    public long id;

    public OrderSide side;
    public int price;
    public int quantity;

/*    @Override
    public String toString() {
        ObjectMapper om = new ObjectMapper();
        try {
            return om.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "null";
    }*/
}
