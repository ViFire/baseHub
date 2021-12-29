package entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class User implements Serializable {

    private static final long serialVersionUID = -2854035784525333365L;

    @Id
    @Column(name = "id", nullable = false)
    private int id;
    private String name;

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
}
