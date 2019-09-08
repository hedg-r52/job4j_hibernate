package ru.job4j.mapping.annotations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class AutoTest {
    private final SessionFactory factory = new Configuration()
            .configure()
            .buildSessionFactory();
    private Session session;

    @Before
    public void setUp() throws Exception {
        session = factory.openSession();
        session.beginTransaction();
    }

    @After
    public void tearDown() throws Exception {
        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void whenAddEditDelete() {
        String nameAuto = "Tesla Model S";
        Auto auto = new Auto(nameAuto, new Body(1), new Engine(1), new Transmission(1));
        session.save(auto);
        Auto added = session.get(Auto.class, auto.getId());
        assertTrue(auto.getId() > 0);
        assertNotNull(added);
        assertThat(added.getName(), is(nameAuto));

        String newNameAuto = "Tesla Model S RX";
        auto.setEngine(new Engine(2));
        auto.setName(newNameAuto);
        Auto updated = session.get(Auto.class, auto.getId());
        assertThat(updated.getName(), is(newNameAuto));
        assertThat(updated.getEngine(), is(new Engine(2)));

        session.delete(auto);
        assertNull(session.get(Auto.class, auto.getId()));
    }

}