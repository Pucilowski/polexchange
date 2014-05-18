package com.pucilowski.exchange.integration.service.server;

import com.pucilowski.exchange.integration.model.in.OrderCancelled;
import com.pucilowski.exchange.integration.model.in.OrderSubmitted;
import org.springframework.stereotype.Service;

/**
 * Created by martin on 18/05/14.
 */

@Service
public class IntegrationIntegrationImpl implements IntegrationIntegration {
    @Override
    public void inputOrder(OrderSubmitted order) {

    }

    @Override
    public void cancelOrder(OrderCancelled order) {

    }
}
