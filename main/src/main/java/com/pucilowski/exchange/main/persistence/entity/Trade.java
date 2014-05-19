package com.pucilowski.exchange.main.persistence.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pucilowski.exchange.common.enums.OrderSide;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by martin on 08/01/14.
 */

@Entity
@Table(name = "trades")
public class Trade {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Market market;

    @Enumerated(EnumType.STRING)
    private OrderSide side;

    @ManyToOne
    //@JoinColumn(name="bid_order_id")
    private Order bidOrder;

    @ManyToOne
    //@JoinColumn(name="ask_order_id")
    private Order askOrder;

    private int price;
    private int quantity;


    @Temporal(TemporalType.TIMESTAMP)
    private Date executed;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public OrderSide getSide() {
        return side;
    }

    public void setSide(OrderSide side) {
        this.side = side;
    }

    public Order getBidOrder() {
        return bidOrder;
    }

    public void setBidOrder(Order bidOrder) {
        this.bidOrder = bidOrder;
    }

    public Order getAskOrder() {
        return askOrder;
    }

    public void setAskOrder(Order askOrder) {
        this.askOrder = askOrder;
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

    public Date getExecuted() {
        return executed;
    }

    public void setExecuted(Date executed) {
        this.executed = executed;
    }

    @Override
    public String toString() {
        ObjectMapper om = new ObjectMapper();
        try {
            return om.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "null";
    }
}
