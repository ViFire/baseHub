package database.repository;

import entities.Account;
import jakarta.persistence.NoResultException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

public class AccountRepository extends AbstractRepository {

    public AccountRepository() {
        super(Account.class);
    }

    public Account findByName(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Account> query = cb.createQuery(Account.class);
        Root<Account> userRoot = query.from(Account.class);

        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery cq = qb.createQuery();
        Root<Account> customer = cq.from(Account.class);

        //Constructing list of parameters
        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(qb.equal(customer.get("name"), name));

        //query itself
        cq.select(customer)
                .where(predicates.toArray(new Predicate[]{}));
        //execute query and do something with result
        try {
            return (Account) em.createQuery(cq).getSingleResult();
        } catch (NoResultException exp) {
            return null;
        }




    }


}
