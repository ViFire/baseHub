package api.security;

import entities.UserRole;
import jakarta.ws.rs.container.DynamicFeature;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.FeatureContext;
import jakarta.ws.rs.ext.Provider;

import java.util.Arrays;
import java.util.List;

/**
 * All available resources have different access permissions. This class loads further filters depending on the target method and its UserRoles.
 * @see UserRoles
 * @see UserRole
 */
@Provider
public class DynamicRequestFilter implements DynamicFeature {

    @Override
    public void configure(ResourceInfo resourceInfo, FeatureContext context) {
        context.register(new LogFilter());

        UserRoles roles = resourceInfo.getResourceMethod().getAnnotation(UserRoles.class);

        if(roles == null) {
            context.register(new NotAvailableResourceFilter());
            return;
        }

        List rolesList = Arrays.asList(roles.values());

        // Resources which can be called without any authentication
        if(rolesList.contains(UserRole.PUBLIC)) {
            return;
        }

        if(rolesList.contains(UserRole.LOGIN)) {
            context.register(new LoginFilter());
            return;
        }

        context.register(new AuthenticationFilter());
        context.register(new AuthorizationFilter());

    }
}
