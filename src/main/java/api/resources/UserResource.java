package api.resources;

import api.context.BasicResourceResponse;
import api.security.UserRoles;
import database.UserRepository;
import entities.User;
import entities.UserRole;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.inject.Inject;
import jakarta.transaction.*;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.text.MessageFormat;
import java.util.Optional;

@Path("/users")
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class UserResource extends BasicResourceResponse {

    @Resource
    private UserTransaction transaction;

    @Inject
    private UserRepository repo;

    @GET
    @Path("id/{userid}")
    @Produces(MediaType.APPLICATION_JSON)
    @UserRoles(values = {UserRole.PUBLIC})
    public Response getUserById(@PathParam("userid") int id) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        Optional<User> user = repo.find(id);

        if(user.isPresent()) {
            response.setPayload(user.get());
        } else {
            response.setResponseToError(null, "No user exist", String.format("No user with id: %s exist", id));
        }
        return createAndSendResponse();
    }

    @GET
    @Path("name/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("userName") String name) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        User user = repo.findByName(name);

        if(user != null) {
            response.setPayload(user);
        } else {
            response.setResponseToError(null, "No user exist", String.format("No user with name: %s exist", name));
        }

        return createAndSendResponse();
    }



    @GET
    @Path("test")
    public String createOrUpdateUser() throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {


        return "ok";
    }

}
