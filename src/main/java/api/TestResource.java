package api;

import database.BasicDao;
import entities.User;
import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.*;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/test")
@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class TestResource {

    @Resource
    private UserTransaction transaction;

    @Inject
    private BasicDao dao;

    @GET
    @Produces("text/plain")
    public String test() {
        User newusr = new User();
        newusr.setName("Test");


        try {
            transaction.begin();
            dao.persist(newusr);
            transaction.commit();
        } catch (NotSupportedException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        } catch (HeuristicRollbackException e) {
            e.printStackTrace();
        } catch (HeuristicMixedException e) {
            e.printStackTrace();
        } catch (RollbackException e) {
            e.printStackTrace();
        }


        return "Test";
    }
}