package api.resources;

import api.context.BasicWebServiceOperation;
import api.security.JWTTokenHelper;
import api.security.UserRoles;
import database.UserRepository;
import entities.User;
import entities.UserRole;
import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.inject.Inject;
import jakarta.transaction.*;
import jakarta.transaction.NotSupportedException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

@Path("/auth")
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class SecurityResource extends BasicWebServiceOperation {

    @Resource
    private UserTransaction transaction;

    @Inject
    private UserRepository repo;

    @GET
    @Path("token")
    @Produces(MediaType.APPLICATION_JSON)
    @UserRoles(values = {UserRole.LOGIN})
    public Response getUserToken(@HeaderParam("Authorization") String authorization) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {

        // TODO
        /*
        if(header == null) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            return;
        }

        Boolean auth = header.indexOf("Basic") == 0 ? true : false;
        String credentials = header.substring(6, header.length()).length() > 0 ? header.substring(6, header.length()) : null;

        if(!auth || credentials == null) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            return;
        }
        */


        loggedInUser = new User();
        loggedInUser.setName("admin");
        loggedInUser.setId(1);
        loggedInUser.setPassword("System32!");
        loggedInUser.encryptPassword();
        loggedInUser.setActive(true);


        if (loggedInUser != null && loggedInUser.isActive()) {
            response.setPayload(JWTTokenHelper.createUserToken(loggedInUser));
        }
        return createAndSendResponse();
    }

}
