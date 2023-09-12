package com.redhat.appfoundation.poc.camel;


import java.util.Random;


import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

import https.www_herongyang_com.service.RegistrationResponse;
import https.www_herongyang_com.service.RegistrationResponse.Confirmation;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CamelRoutes extends RouteBuilder {

    public static Random random = new Random();

    @Override
    public void configure() {

        //from("cxf:registration?cxfConfigurer=#wssecurity&dataFormat=PAYLOAD")
        from("cxf:bean:registration")
        .routeId("soapmock-route")
        .log("CXF Mock : Received request ")
        .log(LoggingLevel.INFO, body().toString())
        .process(e -> {
            RegistrationResponse r = new RegistrationResponse();
            Confirmation c = new Confirmation();
            c.setGuest("Ramalho");
            c.setEvent(""+Math.abs(random.nextInt()));
            r.getConfirmation().add(c);
            e.getMessage().setBody(r);
        })
        .log("CXF Mock : Response successfully created")
        .convertBodyTo(RegistrationResponse.class)
	    .marshal().jaxb();

    }

}
