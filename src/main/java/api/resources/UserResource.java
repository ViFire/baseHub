package api.resources;

import api.context.BasicWebServiceOperation;
import api.security.UserRoles;
import database.UserDao;
import entities.User;
import entities.UserRole;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.*;
import jakarta.transaction.NotSupportedException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.modelmapper.ModelMapper;

import java.util.Optional;
import java.util.Set;

@Path("/users")
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class UserResource extends BasicWebServiceOperation {

    @Inject
    private UserDao repo;

    @Inject
    private UserDao dao;

    @GET
    @Path("id/{userid}")
    @Produces(MediaType.APPLICATION_JSON)
    @UserRoles(values = {UserRole.ADMIN})
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



    @POST
    @Produces("application/json")
    @UserRoles(values = {UserRole.ADMIN})
    public Response createOrUpdateUser(UserDTO dto) throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        ModelMapper modelMapper = new ModelMapper();
        User newusr = modelMapper.map(dto, User.class);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> constraintViolations = validator.validate( newusr );

        if(!constraintViolations.isEmpty()) {
            response.setResponseToError(newusr,"INVALID_USER", "User with constraint violations");
        }

        try {
                dao.persist(newusr);
        } catch (PersistenceException e) {
            response.setResponseToError(newusr,"Error", "Could not update or create user");
        }

        //List<User> test = repo.findAll();


        return createAndSendResponse();
    }

}
