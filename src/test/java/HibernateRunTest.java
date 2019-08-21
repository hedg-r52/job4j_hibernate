import models.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class HibernateRunTest {

    private SessionFactory factory;
    private final HibernateRun hibernateRun = new HibernateRun();

    @Before
    public void setUp() throws Exception {
        this.factory = new Configuration()
                .configure()
                .buildSessionFactory();
        hibernateRun.deleteAll(factory);
    }

    @After
    public void tearDown() throws Exception {
        factory.close();
    }

    @Test
    public void whenCreateUserThenNameSameAsFound() {
        final String name = "Covax";
        hibernateRun.createUser(factory, name);
        User user = hibernateRun.findUser(factory, name);
        assertThat(user.getName(), is(name));
    }

    @Test
    public void whenUpdateUserThenFoundUserHasNewName() {
        final String name = "Bob";
        final String newName = "Muzzy";
        hibernateRun.createUser(factory, name);
        User user = hibernateRun.findUser(factory, name);
        hibernateRun.updateUser(factory, user.getId(), newName);
        User updatedUser = hibernateRun.findUserById(factory, user.getId());
        assertThat(updatedUser.getName(), is(newName));
    }

    @Test
    public void whenDeleteUserThenNoFoundUsers() {
        final String name = "Silvia";
        hibernateRun.createUser(factory, name);
        User user = hibernateRun.findUser(factory, name);
        hibernateRun.deleteUser(factory, user.getId());
        User foundUser = hibernateRun.findUser(factory, name);
        assertNull(foundUser);
    }
}
