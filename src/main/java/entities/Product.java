package entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    @OneToMany
    @JoinColumn(name="product_identifier")
    private Set<ProductIdentifier> productIdentifier = new HashSet<>();


    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ProductIdentifier> getRoles() {
        return productIdentifier;
    }

    public void setRoles(Set<ProductIdentifier> productIdentifier) {
        this.productIdentifier = productIdentifier;
    }
}
