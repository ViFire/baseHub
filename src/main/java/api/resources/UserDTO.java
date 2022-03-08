package api.resources;

import entities.Account;
import entities.UserRole;

import java.util.HashSet;
import java.util.Set;

public class UserDTO {

    private int id;
    private String name;
    private String password;
    private boolean isActive;
    private Set<UserRole> roles = new HashSet<>();
    private Account account;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
