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
    public Long id;

    @ManyToOne
    public Market market;

    @Enumerated(EnumType.STRING)
    public OrderSide side;

    @ManyToOne
    //@JoinColumn(name="bid_order_id")
    public Order bid;

    @ManyToOne
    //@JoinColumn(name="ask_order_id")
    public Order ask;



    public int quantity;
    public int price;


    @Temporal(TemporalType.TIMESTAMP)
    public Date executed;


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
