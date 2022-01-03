package api.security;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;

import java.io.IOException;
import java.util.UUID;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class AuthenticationFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Logger log = (Logger) requestContext.getProperty("logger");
        log.info("Authentication: "+log.getName());

    }
}
