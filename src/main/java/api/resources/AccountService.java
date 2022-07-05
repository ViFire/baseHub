package api.resources;

import api.context.BasicWebServiceOperation;
import api.security.UserRoles;
import database.AccountDao;
import database.UserDao;
import entities.Account;
import entities.User;
import entities.UserRole;
import jakarta.annotation.Resource;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.PersistenceException;
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

@Path("/account")
public class AccountService extends BasicWebServiceOperation {

    @Inject
    private AccountDao repo;

    @Inject
    private AccountDao dao;

    @GET
    @Path("id/{accountId}")
    @Produces(MediaType.APPLICATION_JSON)
    @UserRoles(values = {UserRole.ADMIN})
    public Response getAccountById(@PathParam("accountId") int id) {
        Optional<Account> account = repo.find(id);

        if (account.isPresent()) {
            response.setPayload(account.get());
        } else {
            response.setResponseToError(null, "No account exist", String.format("No user with id: %s exist", id));
        }
        return createAndSendResponse();
    }
}
