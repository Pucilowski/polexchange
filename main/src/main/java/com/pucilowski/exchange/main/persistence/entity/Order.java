package com.pucilowski.exchange.main.persistence.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pucilowski.exchange.common.enums.OrderStatus;
import com.pucilowski.exchange.common.enums.OrderSide;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by martin on 08/01/14.
 */


@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @ManyToOne
    public User user;

    @ManyToOne
    public Market market;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public OrderSide side;

    @Column(updatable = false)
    public int quantity;

    @Column(updatable = false)
    public int price;

    public int remaining;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public OrderStatus status;


    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(insertable = false, updatable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    public Date submitted = null;

    public Long getId() {
        return id;
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
