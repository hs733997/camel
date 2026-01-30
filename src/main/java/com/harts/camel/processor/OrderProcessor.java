package com.harts.camel.processor;

import com.harts.camel.dto.Order;
import com.harts.camel.service.OrderService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class OrderProcessor implements Processor {

    private final OrderService orderService;

    public OrderProcessor(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        orderService.addOrder(exchange.getIn().getBody(Order.class));
    }
}
