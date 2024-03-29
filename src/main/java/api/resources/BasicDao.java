package api.resources;

import entities.User;
import jakarta.annotation.PostConstruct;
import jakarta.interceptor.Interceptors;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.validation.*;
import org.javers.spring.annotation.JaversAuditable;


import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * This dao should be use for simple database interactions. In other cases use repositories.
 */

public abstract class BasicDao<T> {

    @PersistenceContext
    protected EntityManager em;
    protected Class<T> inferredClass;

    public BasicDao() {

    }
    public BasicDao(Class<T> inferredClass) {
        this.inferredClass = inferredClass;
    }

    public void persist(T entity) {
        if(em.contains(entity)) {
            em.merge(entity);
        } else {
            em.persist(entity);
        }
    }

    public void delete(T entity) {
        em.remove(entity);
    }

    public Optional<T> find(int id) {
        return Optional.of(em.find(inferredClass,id));
    }

    public List<T> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(inferredClass);
        Root<T> rootEntry = cq.from(inferredClass);
        CriteriaQuery<T> all = cq.select(rootEntry);
        TypedQuery<T> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

}
