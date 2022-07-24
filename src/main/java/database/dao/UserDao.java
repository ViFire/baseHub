package database.dao;

import entities.User;
import jakarta.inject.Named;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

@Named
public class UserDao extends BasicDao<User> {

    public UserDao() {
        super(User.class);
    }

    public UserDao(Class<User> inferredClass) {
        super(inferredClass);
    }

    public User findUserByName(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> userRoot = query.from(User.class);

        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery cq = qb.createQuery();
        Root<User> customer = cq.from(User.class);

        //Constructing list of parameters
        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(qb.equal(customer.get("name"), name));

        //query itself
        cq.select(customer)
                .where(predicates.toArray(new Predicate[]{}));
        //execute query and do something with result
        try {
            return (User) em.createQuery(cq).getSingleResult();
        } catch (NoResultException exp) {
            return null;
        }
    }
}
