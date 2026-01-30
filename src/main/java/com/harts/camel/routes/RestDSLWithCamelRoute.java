package com.harts.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RestDSLWithCamelRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        rest("/say")
                .get("/hello").to("direct:hello")
                .get("/bye").consumes("application/json").to("direct:bye")
                .post("/bye").to("mock:update");

        from("direct:hello")
                .transform().constant("Hello World");

        from("direct:bye")
                .transform().constant("Bye World");
    }
}
