package com.harts.camel.routes;

import com.harts.camel.dto.Order;
import com.harts.camel.processor.OrderProcessor;
import com.harts.camel.service.OrderService;
import org.apache.camel.BeanInject;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class OrderRoute extends RouteBuilder {

    private final OrderService orderService;
    @BeanInject
    private OrderProcessor orderProcessor;

    public OrderRoute(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void configure() throws Exception {
        rest().get("/orders").produces(MediaType.APPLICATION_JSON_VALUE).to("direct:orderRoute");

        from("direct:orderRoute")
                .log(orderService.getOrders().toString())
                .process(exchange -> exchange.getIn().setBody(orderService.getOrders()));

        rest().post("/order").consumes(MediaType.APPLICATION_JSON_VALUE).type(Order.class)
                .outType(Order.class).produces(MediaType.APPLICATION_JSON_VALUE)
                .to("direct:addOrderRoute");

        from("direct:addOrderRoute")
                .process(orderProcessor)
                .end();
    }
}
