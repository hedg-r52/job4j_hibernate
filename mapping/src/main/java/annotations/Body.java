package annotations;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "auto_bodies")
public class Body {
    private long id;
    private String name;
    private Set<Auto> autos = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "name", unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "body")
    public Set<Auto> getAutos() {
        return this.autos;
    }

    public void setAutos(Set<Auto> autos) {
        this.autos = autos;
    }

    public Body() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Body body = (Body) o;
        return id == body.id && Objects.equals(name, body.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
