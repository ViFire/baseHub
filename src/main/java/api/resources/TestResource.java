package api.resources;

import api.context.BasicWebServiceOperation;
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
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Path("/test")
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class TestResource extends BasicWebServiceOperation {

    @Resource
    private UserTransaction transaction;

    @Inject
    private UserRepository repo;

    @GET
    @Produces("text/plain")
    @Path("test1")
    @UserRoles(values = {UserRole.ADMIN})
    public String test() throws SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        User newusr = new User();
        newusr.setName("Test");
        newusr.setPassword("1234");
        Set<UserRole> roles = new HashSet<>();
        roles.add(UserRole.ADMIN);
        roles.add(UserRole.PUBLIC);
        newusr.setRoles(roles);

        transaction.begin();
        repo.create(newusr);
        transaction.commit();

        List<User> test = repo.findAll();


        return "Test";
    }
}