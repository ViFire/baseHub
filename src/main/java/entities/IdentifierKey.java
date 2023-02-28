package entities;
import jakarta.persistence.*;

@Entity
public class IdentifierKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String identifierKey;

    public IdentifierKey() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentifierKey() {
        return identifierKey;
    }

    public void setIdentifierKey(String identifierKey) {
        this.identifierKey = identifierKey;
    }

}
