package api.resources;

import api.security.UserRoles;
import entities.UserRole;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.Date;


@Path("/heartbeat")
public class Heartbeat {
    @GET
    @UserRoles(values = {UserRole.PUBLIC})
    public String getHeartBeat() {
        return new Date().toString();
    }

}
