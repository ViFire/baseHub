package api.security;

import entities.UserRole;
import jakarta.ws.rs.container.DynamicFeature;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.FeatureContext;
import jakarta.ws.rs.ext.Provider;

import java.util.Arrays;
import java.util.List;

@Provider
public class DynamicRequestFilter implements DynamicFeature {

    @Override
    public void configure(ResourceInfo resourceInfo, FeatureContext context) {
        context.register(new LogFilter());

        UserRoles roles = resourceInfo.getResourceMethod().getAnnotation(UserRoles.class);

        if(roles == null) {
            context.register(new NotAvailableResourceFilter());
        } else {
            List rolesList = Arrays.asList(roles.values());

            if(rolesList.contains(UserRole.PUBLIC)) {
                return;
            } else {
                context.register(new AuthenticationFilter());
                context.register(new AuthorizationFilter());
            }
         }
    }
}
