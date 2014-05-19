package com.pucilowski.exchange.api.response;

//import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * Created by martin on 06/02/14.
 */

//@JsonAutoDetect

public class MarketDTO {

    private String base;
    private String counter;

    public MarketDTO() {
    }

    public MarketDTO(String base, String counter) {
        this.base = base;
        this.counter = counter;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }
}
