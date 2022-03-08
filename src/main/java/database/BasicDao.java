package database;

import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.*;

import java.util.Optional;

/**
 * This dao should be use for simple database interactions. In other cases use repositories.
 *
 * @param <T> the type parameter for the entity class
 * STATUS_ACTIVE               0
 * STATUS_COMMITTED            3
 * STATUS_COMMITTING           8
 * STATUS_MARKED_ROLLBACK      1
 * STATUS_NO_TRANSACTION       6
 * STATUS_PREPARED             2
 * STATUS_PREPARING            7
 * STATUS_ROLLEDBACK           4
 * STATUS_ROLLING_BACK         9
 * STATUS_UNKNOWN              5
 *
 */
public abstract class BasicDao<T> {

    @Resource protected UserTransaction transaction;
    @PersistenceContext protected EntityManager em;
    protected Class<T> inferredClass;

    public BasicDao() {}
    public BasicDao(Class<T> inferredClass) {
        this.inferredClass = inferredClass;
    }

    private void beginTransaction() {
        try {
            System.out.println(transaction.getStatus());
            transaction.begin();
            System.out.println(transaction.getStatus());
        } catch (NotSupportedException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        }
    }
    private void closeTransaction() {
        try {
            System.out.println(transaction.getStatus());
            if(transaction.getStatus() == 0) {
                transaction.commit();
            } else {
                transaction.rollback();
            }
            System.out.println(transaction.getStatus());
        } catch (RollbackException e) {
            e.printStackTrace();
        } catch (HeuristicRollbackException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        } catch (HeuristicMixedException e) {
            e.printStackTrace();
        } finally {

        }
    }


    public void persist(T entity) {
        beginTransaction();
        if(em.contains(entity)) {
            em.merge(entity);
        } else {
            em.persist(entity);
        }
        closeTransaction();
    }

    public Optional<T> find(int id) {
        return Optional.of(em.find(inferredClass,id));
    }

}
