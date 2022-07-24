package entities;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_identifier_ids",
            joinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "identifier_id", referencedColumnName = "id")})
    @MapKey(name = "identValue")
    private Map<String, ProductIdentifier> identifierMap = new HashMap();

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

    public Map<String, ProductIdentifier> getIdentifierMap() {
        return identifierMap;
    }

    public void setIdentifierMap(Map<String, ProductIdentifier> identifierMap) {
        this.identifierMap = identifierMap;
    }

    public void addIdentifierMap(ProductIdentifier identifier) {
        identifierMap.put(identifier.getIdentName().getIdentName(),identifier);
    }
}
