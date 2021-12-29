package api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/test")
public class TestResource {
    @GET
    @Produces("text/plain")
    public String test() {
        return "Hello, World!";
    }
}