package api.resources.account;

import api.context.BasicWebServiceOperation;
import api.security.UserRoles;
import entities.Account;
import entities.UserRole;
import jakarta.inject.Inject;
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

    @POST
    @Produces("application/json")
    @UserRoles(values = {UserRole.ADMIN})
    public Response createOrUpdateAccount(AccountDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Account acc = modelMapper.map(dto, Account.class);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Account>> constraintViolations = validator.validate(acc);

        if (!constraintViolations.isEmpty()) {
            response.setResponseToError(acc, "INVALID_ACCOUNT", "Account with constraint violations");
        }

        try {
            repo.persist(acc);
        } catch (Exception e) {
            response.setResponseToError(acc, "Error", "Could not update or create account");
        }

        return createAndSendResponse();
    }
}
