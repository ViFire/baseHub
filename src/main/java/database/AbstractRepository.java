package database;

import entities.AbstractBaseEntity;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.*;

import java.util.List;
import java.util.Optional;

public class AbstractRepository<T extends AbstractBaseEntity> {

    @Resource protected UserTransaction transaction;
    @PersistenceContext protected EntityManager em;

    protected Class<T> inferredClass;

    public AbstractRepository(Class<T> inferredClass) {
        this.inferredClass = inferredClass;
    }

    //TODO implement longconversation and add begintransaction to create operator
    private void beginTransaction() {
        try {
            transaction.begin();
        } catch (NotSupportedException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        }
    }
    private void closeTransaction() {
        try {
            transaction.commit();
        } catch (RollbackException e) {
            e.printStackTrace();
        } catch (HeuristicMixedException e) {
            e.printStackTrace();
        } catch (HeuristicRollbackException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        }
    }

    public void create(T entity) {
        em.persist(entity);
    }

    public Optional<T> find(int id) {
        return Optional.of(em.find(inferredClass,id));
    }

    public List<T> findAll() {
        return null;
    }


}
