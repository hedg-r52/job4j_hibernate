package persistent;

import models.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DBStore implements Store {
    private final static DBStore INSTANCE = new DBStore();
    private final SessionFactory factory;

    private DBStore() {
        this.factory = new Configuration().configure().buildSessionFactory();
    }

    public static DBStore getInstance() {
        return INSTANCE;
    }

    @Override
    public long add(Item item) {
        item.setCreated(new Timestamp(System.currentTimeMillis()));
        item.setDone(false);
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        }
        return item.getId();
    }

    @Override
    public void update(Item item) {
        try (Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
        }
    }

    @Override
    public void delete(Item item) {
        try (Session session = factory.openSession()) {
            item.setDone(false);
            session.beginTransaction();
            session.delete(item);
            session.getTransaction().commit();
        }
    }

    @Override
    public Item findItem(long id) {
        List<Item> items = new ArrayList<>();
        try (Session session = factory.openSession()) {
            items = session.createQuery("from Item where id=:id")
                    .setParameter("id", id)
                    .list();
        }
        return (items.size() > 0 ? items.get(0) : null);
    }

    @Override
    public Item findItem(String description) {
        List<Item> items = new ArrayList<>();
        try (Session session = factory.openSession()) {
            items = session.createQuery("from Item where description=:description")
                    .setParameter("description", description)
                    .list();
        }
        return (items.size() > 0 ? items.get(0) : null);
    }

    @Override
    public List<Item> allItems() {
        Session session = factory.openSession();
        List<Item> result = session.createQuery("from Item order by created").list();
        session.close();
        return result;
    }
}
