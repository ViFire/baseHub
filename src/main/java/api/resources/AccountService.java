package api.resources;

import api.context.BasicWebServiceOperation;
import api.security.UserRoles;
import database.dao.AccountDao;
import database.repository.AccountRepository;
import entities.Account;
import entities.UserRole;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

@Path("/account")
public class AccountService extends BasicWebServiceOperation {

    @Inject
    private AccountRepository repo;

    //@Inject
    //private AccountDao dao;

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
