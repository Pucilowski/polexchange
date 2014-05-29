package com.pucilowski.exchange.api.response;

/**
 * Created by martin on 06/02/14.
 */

//@JsonAutoDetect
public class MarketOrder {

    private int price;
    private int quantity;

    public MarketOrder() {

    }

    public MarketOrder(int price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

 /*   @Override
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
