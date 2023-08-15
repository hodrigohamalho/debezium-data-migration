package org.acme;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.jboss.resteasy.reactive.RestStreamElementType;

import io.smallrye.mutiny.Multi;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;

@Path("/respostas")
public class GreetingResource {
    
    @Inject
    @Channel("respostas-in")
    Multi<Resposta> respostas;

    @GET
    @RestStreamElementType(MediaType.APPLICATION_JSON)
    // by using @RestStreamElementType, we don't need to add @Produces(MediaType.SERVER_SENT_EVENTS)
    public Multi<Resposta> stream() {
        return respostas;
    }
}
