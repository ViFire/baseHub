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
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import java.util.HashSet;
import java.util.Set;

@Path("/test")
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class TestResource extends BasicWebServiceOperation {

    @Inject
    private UserDao dao;

    @GET
    @Produces("text/plain")
    @Path("test1")
    @UserRoles(values = {UserRole.ADMIN})
    public String test() {
        User newusr = new User();
        newusr.setName("Test");
        newusr.setPassword("1234");
        Set<UserRole> roles = new HashSet<>();
        roles.add(UserRole.ADMIN);
        roles.add(UserRole.PUBLIC);
        newusr.setRoles(roles);

        try {
            dao.persist(newusr);
        } catch (PersistenceException e) {
            System.out.println("fail");
            System.out.println(e.getMessage());
            System.out.println(e.getCause());

            System.out.println(e.getCause().getMessage());
            System.out.println(e.getCause().getCause());
        }

        //List<User> test = repo.findAll();


        return "Test";
    }
}