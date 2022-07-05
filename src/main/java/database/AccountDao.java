package database;

import entities.Account;
import jakarta.inject.Named;

@Named
public class AccountDao extends BasicDao<Account> {

    public AccountDao() {
        super(Account.class);
    }

    public AccountDao(Class<Account> inferredClass) {
        super(inferredClass);
    }

}
