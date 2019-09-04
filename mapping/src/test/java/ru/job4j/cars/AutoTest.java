package ru.job4j.cars;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AutoTest {
    private static SessionFactory factory = new Configuration()
            .configure()
            .buildSessionFactory();
    private Session session;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void whenAdd() {
        String nameAuto = "Tesla Model S";
        Auto auto = new Auto(nameAuto, new Body(1), new Engine(3), new Transmission(4));
        session = factory.openSession();
        session.beginTransaction();
        session.save(auto);
        Auto added = session.get(Auto.class, auto.getId());
        assertTrue(auto.getId() > 0);
        assertNotNull(added);
        assertThat(added.getName(), is(nameAuto));
        session.getTransaction().commit();
        session.close();
    }
}