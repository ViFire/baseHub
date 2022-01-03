package api.security;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.util.logging.Logger;

public class NotAvailableResourceFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
    }
}
