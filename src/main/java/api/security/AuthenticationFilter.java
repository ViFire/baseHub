package api.security;


import database.UserRepository;
import entities.User;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.util.logging.Logger;


/**
 * Validate if request contains authorization header with a valid JWT token.
 * Otherwise requestes will be abborded.
 */
public class AuthenticationFilter implements ContainerRequestFilter {

    private UserRepository repo = CDI.current().select((UserRepository.class)).get();

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Logger log = (Logger) requestContext.getProperty("logger");
        log.info("Authentication");

        String authHeader = requestContext.getHeaderString("Authorization");
        if(authHeader == null) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            return;
        }

        boolean authType = authHeader.indexOf("Bearer") == 0 ? true : false;
        String credentials = authHeader.substring(7, authHeader.length()).length() > 0 ? authHeader.substring(7, authHeader.length()) : null;

        if(!authType || !JWTTokenHelper.validateUserToken(credentials)) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            return;
        }

        int claimedUserId = JWTTokenHelper.getUserIdFromClaim(credentials);
        User claimedUser = (User) repo.find(claimedUserId).orElse(null);

        if(claimedUser == null || !claimedUser.isActive() || claimedUser.getId() < 1) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            return;
        }

        requestContext.setProperty("claimedUser", claimedUser);
    }
}
