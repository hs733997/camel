package com.harts.camel.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class ApplicationResource extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        log.info("route started");

        restConfiguration().component("servlet").port(8080).host("localhost")
                .bindingMode(RestBindingMode.json);

        rest().get("/hello").produces(MediaType.APPLICATION_JSON_VALUE).to("direct:helloRoute");

        from("direct:helloRoute")
                .setBody(constant("Welcome to Camel World"));

        log.info("route ended");
    }
}
