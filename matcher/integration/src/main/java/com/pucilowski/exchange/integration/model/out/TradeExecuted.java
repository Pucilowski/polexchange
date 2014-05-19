package com.pucilowski.exchange.integration.model.out;

/**
 * Created by martin on 13/05/14.
 */
public class TradeExecuted {

    long bidOrderId;
    long askOrderId;
    int price;
    int quantity;

    public TradeExecuted() {
    }

    public long getBidOrderId() {
        return bidOrderId;
    }

    public void setBidOrderId(long bidOrderId) {
        this.bidOrderId = bidOrderId;
    }

    public long getAskOrderId() {
        return askOrderId;
    }

    public void setAskOrderId(long askOrderId) {
        this.askOrderId = askOrderId;
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

    public TradeExecuted(long bidOrderId, long askOrderId, int price, int quantity) {
        this.bidOrderId = bidOrderId;
        this.askOrderId = askOrderId;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "TradeExecuted{" +
                "bidOrderId=" + bidOrderId +
                ", askOrderId=" + askOrderId +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
