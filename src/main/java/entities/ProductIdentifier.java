package entities;
import jakarta.persistence.*;

@Entity
@Table(name = "product_identifier")
public class ProductIdentifier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Identifier identName;

    @Column(unique = true)
    private String identValue;

    public ProductIdentifier() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Identifier getIdentName() {
        return identName;
    }

    public void setIdentName(Identifier identName) {
        this.identName = identName;
    }

    public String getIdentValue() {
        return identValue;
    }

    public void setIdentValue(String identValue) {
        this.identValue = identValue;
    }
}
