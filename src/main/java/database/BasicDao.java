package database;

import entities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class BasicDao {

    @PersistenceContext
    private EntityManager em;

    public void persist(User user) {
        em.persist(user);
    }



}
