package api.security;

import database.repository.UserRepository;
import entities.User;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.ws.rs.core.SecurityContext;
import org.apache.commons.codec.binary.Base64;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.logging.Logger;


/**
 * The type Login filter.
 */
public class LoginFilter implements ContainerRequestFilter {

    private UserRepository repo = CDI.current().select((UserRepository.class)).get();

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Logger log = (Logger) requestContext.getProperty("logger");
        log.info("Login: "+log.getName());

        String authorization = requestContext.getHeaderString("Authorization");

        if(authorization == null || authorization.indexOf("Basic") != 0) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            return;
        }

        String base64Credentials = authorization.substring("Basic".length()).trim();

        byte[] credDecoded = Base64.decodeBase64(base64Credentials);
        String credentials = new String(credDecoded, StandardCharsets.UTF_8);
        // credentials = username:password
        final String[] values = credentials.split(":", 2);

        User loginUser = new User();
        loginUser.setName(values[0]);
        loginUser.setPassword(values[1]);

        if(!isClaimedUserAuthorized(loginUser)) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            return;
        } else {
            SecurityContext secContext = new SecurityContext() {
                @Override
                public Principal getUserPrincipal() {
                    return repo.findByName(loginUser.getName());
                }

                @Override
                public boolean isUserInRole(String role) {
                    return false;
                }

                @Override
                public boolean isSecure() {
                    return false;
                }

                @Override
                public String getAuthenticationScheme() {
                    return "Basic Auth";
                }
            };
            requestContext.setSecurityContext(secContext);
        }
    }

    /*
    Check if the claimed user match with a user in database and validate against each other
     */
    private boolean isClaimedUserAuthorized(User claimedUser) {
        User existingUser = repo.findByName(claimedUser.getName());

        // find user, is active, passwort valid
        if(existingUser == null || !existingUser.isActive() || !PasswordHelper.validatePassword(claimedUser.getPassword(),existingUser.getPassword()))
            return false;

        return true;
    }
}
