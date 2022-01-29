package entities;

import jakarta.persistence.*;

public enum UserRole {

    ADMIN("admin", "Full access"),
    PUBLIC("public", "Endpoint available for all"),
    LOGIN("login", "Granted to login and get token"),
    HEARTBEAT("heartbeat", "test for api");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "role")
    private String role;
    private String description;

    UserRole(String role, String description) {
        this.role = role;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
