package com.pucilowski.exchange.main.web.api.response.world;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by martin on 06/02/14.
 */

@JsonAutoDetect
public class OrderbookDTO {

    private List<OrderDTO> bids = new ArrayList<>();
    private List<OrderDTO> asks = new ArrayList<>();

    public OrderbookDTO() {

    }

    public OrderbookDTO(List<OrderDTO> bids, List<OrderDTO> asks) {
        this.bids = bids;
        this.asks = asks;
    }

    public List<OrderDTO> getBids() {
        return bids;
    }

    public void setBids(List<OrderDTO> bids) {
        this.bids = bids;
    }

    public List<OrderDTO> getAsks() {
        return asks;
    }

    public void setAsks(List<OrderDTO> asks) {
        this.asks = asks;
    }
}
