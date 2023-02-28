package api.resources.security;

import api.context.BasicWebServiceOperation;
import api.security.UserRoles;
import entities.User;
import entities.UserRole;
import jakarta.inject.Inject;
import jakarta.validation.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.modelmapper.ModelMapper;

import java.util.Optional;
import java.util.Set;

@Path("/users")
public class UserService extends BasicWebServiceOperation {

    @Inject
    private UserDao repo;

    @GET
    @Path("id/{userid}")
    @Produces(MediaType.APPLICATION_JSON)
    @UserRoles(values = {UserRole.ADMIN})
    public Response getUserById(@PathParam("userid") int id) {
        Optional<User> user = repo.find(id);

        if (user.isPresent()) {
            response.setPayload(user.get());
        } else {
            response.setResponseToError(null, "No user exist", String.format("No user with id: %s exist", id));
        }
        return createAndSendResponse();
    }

    @GET
    @Path("name/{userName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("userName") String name) {
        User user = repo.findUserByName(name);
        if (user != null) {
            response.setPayload(user);
        } else {
            response.setResponseToError(null, "No user exist", String.format("No user with name: %s exist", name));
        }
        return createAndSendResponse();
    }


    @POST
    @Produces("application/json")
    @UserRoles(values = {UserRole.ADMIN})
    public Response createOrUpdateUser(UserDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        User newusr = modelMapper.map(dto, User.class);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(newusr);

        if (!constraintViolations.isEmpty()) {
            response.setResponseToError(newusr, "INVALID_USER", "User with constraint violations");
        }

        try {
            repo.persist(newusr);
        } catch (Exception e) {
            response.setResponseToError(newusr, "Error", "Could not update or create user");
        }

        return createAndSendResponse();
    }


}
