package org.acme;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.acme.backport.Confirmation;
import org.apache.camel.Message;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import https.www_herongyang_com.service.RegistrationRequest;
import https.www_herongyang_com.service.RegistrationResponse;

@ApplicationScoped
public class MyRouteBuilder extends org.apache.camel.builder.RouteBuilder{

    @ConfigProperty(name = "app.webservice.soap.url")
    private String soapurl;

    @Override
    public void configure() throws Exception {
        JaxbDataFormat df = new JaxbDataFormat();
        df.setContextPath("https.www_herongyang_com.service");
   
        // from("timer:geradorMensagens")
        //     .setBody(simple("{\"nome\": \"Vinicius-${random(1,999)}\",\"id\": ${random(1,99999)}, \"origem\": \"quarkus\"}"))
        //     // .marshal().json(JsonLibrary.Jackson)
        //     .to("kafka:eventos");

        from("direct:soapRequest")
            .convertBodyTo(RegistrationRequest.class)
            .setHeader(CxfConstants.OPERATION_NAME, constant("Registration"))
            .setHeader(CxfConstants.OPERATION_NAMESPACE, constant("https://www.herongyang.com/Service/"))
            .marshal().jaxb()

            .log("SOAP request")
            .log(body().toString())

            .toD("cxf:" + soapurl + "?serviceClass=https.www_herongyang_com.service.RegistrationPortType&exchangePattern=InOut&dataFormat=PAYLOAD")

            .log("SOAP response")
            .log(body().toString())

            .unmarshal(df)

            .process(e -> {
                Message m = e.getIn();
                RegistrationResponse soapresponse = (RegistrationResponse) m.getBody();

                Confirmation c = new Confirmation();
                c.setEvent(soapresponse.getConfirmation().get(0).getEvent().toString());
                c.setGuest(soapresponse.getConfirmation().get(0).getGuest().toString());

                m.setBody(c);
            });
    }
    
}
