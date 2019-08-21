import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import java.sql.Timestamp;
import java.util.List;

/**
 * class HibernateRun
 * @author Andrei Soloviev (hedg.r52@gmail.com)
 * @since 21.08.2019
 */
public class HibernateRun {
    private static final long YEAR = 1000L * 60 * 60 * 24 * 365;

    /**
     * Create user via Hibernate
     * @param name username
     * @param factory factory
     */
    public void createUser(SessionFactory factory, String name) {
        User user = new User();
        user.setName(name);
        user.setExpired(new Timestamp(System.currentTimeMillis() + YEAR));
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Find user via Hibernate
     * @param factory factory
     * @param name searching name
     * @return user entity
     */
    public User findUser(SessionFactory factory, String name) {
        Session session = factory.openSession();
        Query query = session.createQuery("from User where name=:name");
        query.setParameter("name", name);
        return (query.list().size() > 0 ? (User) query.iterate().next() : null);
    }

    /**
     * Update user via Hibernate
     * @param id user's id for updating
     * @param name new name
     * @param factory factory
     */
    public void updateUser(SessionFactory factory, int id, String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setExpired(new Timestamp(System.currentTimeMillis() + YEAR));
        Session session = factory.openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Delete user via Hibernate
     * @param id user's id for deleting
     * @param factory factory
     */
    public void deleteUser(SessionFactory factory, int id) {
        User user = new User();
        user.setId(id);
        Session session = factory.openSession();
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Find all users via Hibernate
     * @param factory factory
     * @return list of usets
     */
    public List<User> findAll(SessionFactory factory) {
        Session session = factory.openSession();
        session.beginTransaction();
        List<User> result = session.createQuery("from User ").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    /**
     * Delete all users via Hibernate
     * @param factory factory
     */
    public void deleteAll(SessionFactory factory) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.createQuery("delete from User").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Find user by id via Hibernate
     * @param factory factory
     * @param id user's id for finding
     * @return user entity
     */
    public User findUserById(SessionFactory factory, int id) {
        Session session = factory.openSession();
        Query query = session.createQuery("from User where id=:id");
        query.setParameter("id", id);
        return (query.list().size() > 0 ? (User) query.iterate().next() : null);
    }
}
