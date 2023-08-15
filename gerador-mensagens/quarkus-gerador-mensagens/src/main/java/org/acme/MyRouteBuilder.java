package org.acme;

public class MyRouteBuilder extends org.apache.camel.builder.RouteBuilder{

    @Override
    public void configure() throws Exception {
        from("timer:geradorMensagens")
            .setBody(simple("{\"nome\": \"Vinicius-${random(1,999)}\",\"id\": ${random(1,99999)}}"))
            // .marshal().json(JsonLibrary.Jackson)
            .to("kafka:eventos");
    }
    
}
