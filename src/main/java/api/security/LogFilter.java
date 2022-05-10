package api.security;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;


import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class LogFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String logId = UUID.randomUUID().toString();
        Logger log = LogManager.getLogManager().getLogger(logId);

        log.log(Level.INFO, "Incoming {0} request on {1}", new Object[]{
                requestContext.getMethod(),
                resourceInfo.getResourceMethod().getName() }
        );
        requestContext.setProperty("logger", log);

    }
}
