package entities;
import jakarta.persistence.*;
@Entity
public class ProductIdentifier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String identifierName;

    public ProductIdentifier() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentifierName() {
        return identifierName;
    }

    public void setIdentifierName(String identName) {
        this.identifierName = identName;
    }

}
