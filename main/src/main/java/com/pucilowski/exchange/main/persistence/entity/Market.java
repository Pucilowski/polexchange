package com.pucilowski.exchange.main.persistence.entity;

import com.pucilowski.exchange.main.persistence.entity.id.CurrencyPair;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by martin on 08/01/14.
 */
@Entity
@Table(name = "markets")
public class Market {

    @EmbeddedId
    private CurrencyPair id;

    public Market() {
    }

    public Market(CurrencyPair id) {
        this.id = id;
    }

    public void setId(CurrencyPair id) {
        this.id = id;
    }

    public CurrencyPair getId() {
        return id;
    }



/*    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;*/

/*    @ManyToOne
    @JoinColumn(name="asset_id")
    private Currency base;

    @ManyToOne
    @JoinColumn(name="denom_id")
    private Currency counter;*/


}
