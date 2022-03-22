package database;

import entities.AbstractBaseEntity;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.*;

import java.util.List;
import java.util.Optional;

public class AbstractRepository<T extends AbstractBaseEntity> {

    //TODO https://www.baeldung.com/java-dao-vs-repository

    @Resource protected UserTransaction transaction;
    @PersistenceContext protected EntityManager em;

    protected Class<T> inferredClass;

    public AbstractRepository(Class<T> inferredClass) {
        this.inferredClass = inferredClass;
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
