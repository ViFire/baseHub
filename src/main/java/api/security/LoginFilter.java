package api.security;

import entities.User;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import org.apache.logging.log4j.core.*;

import java.io.IOException;


public class LoginFilter implements ContainerRequestFilter {

    private User loggedInUser = null;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Logger log = (Logger) requestContext.getProperty("logger");
        log.info("Login: "+log.getName());

        return;



    }
}
