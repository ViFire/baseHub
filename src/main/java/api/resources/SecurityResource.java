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
public class SecurityResource extends BasicWebServiceOperation {

    @Inject
    private UserRepository repo;

    @GET
    @Path("token")
    @Produces(MediaType.APPLICATION_JSON)
    @UserRoles(values = {UserRole.LOGIN})
    public Response getUserToken() throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        String token = JWTTokenHelper.createUserToken(claimedUser);
        response.setPayload(token);
        return createAndSendResponse();
    }

}
