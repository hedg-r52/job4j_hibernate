package annotations;

import javax.persistence.*;

@Entity
@Table(name = "auto_autos")
public class Auto {

    private long id;
    private String name;
    private Body body;
    private Engine engine;
    private Transmission transmission;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "body_id", referencedColumnName = "id")
    public Body getBody() {
        return this.body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "engine_id", referencedColumnName = "id")
    public Engine getEngine() {
        return this.engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transmission_id", referencedColumnName = "id")
    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public Auto() {
    }

    public Auto(String name) {
        this.name = name;
    }
}
