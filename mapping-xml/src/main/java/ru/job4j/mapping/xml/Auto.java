package ru.job4j.mapping.xml;

public class Auto {

    private long id;
    private String name;
    private Body body;
    private Engine engine;
    private Transmission transmission;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Body getBody() {
        return this.body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Engine getEngine() {
        return this.engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public Auto() {
    }

    public Auto(String name, Body body, Engine engine, Transmission transmission) {
        this.name = name;
        this.body = body;
        this.engine = engine;
        this.transmission = transmission;
    }
}
