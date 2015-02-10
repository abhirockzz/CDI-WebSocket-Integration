package rest;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/feed")
public class RESTFeed {

    @Inject
    Event<String> event;

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public void push(String msg) {
        event.fire(msg);
    }
}
