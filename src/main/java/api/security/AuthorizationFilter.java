package api.security;

import entities.User;
import entities.UserRole;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.util.logging.Logger;

import jakarta.ws.rs.core.SecurityContext;


/**
 * Validate if claimed user has access to the requested ressource
 */
public class AuthorizationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Logger log = (Logger) requestContext.getProperty("logger");
        log.info("Authorization: "+log.getName());

        User claimedUser = (User) requestContext.getSecurityContext().getUserPrincipal();

        UserRoles annotation = resourceInfo.getResourceMethod().getAnnotation(UserRoles.class);
        UserRole role = annotation.values()[0];

        if(!((claimedUser.hasRole(role) && claimedUser.hasRole(UserRole.LOGIN)) || claimedUser.hasRole(UserRole.ADMIN))) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }

    }
}
