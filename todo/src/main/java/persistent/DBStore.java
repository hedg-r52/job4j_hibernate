package persistent;

import models.Item;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.sql.Timestamp;
import java.util.List;
import java.util.function.Function;

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
        return this.tx(session -> {
            session.save(item);
            return item.getId();
        });
    }

    @Override
    public void update(Item item) {
        this.tx(session -> {
            session.update(item);
            return null;
        });
    }

    @Override
    public void delete(Item item) {
        this.tx(session -> {
            session.delete(item);
            return null;
        });
    }

    @Override
    public Item findItem(long id) {
        return this.tx(session -> {
            List<Item> items =  session.createQuery("from Item where id=:id")
                                    .setParameter("id", id)
                                    .list();
            return (items.size() > 0 ? items.get(0) : null);
        });
    }

    @Override
    public Item findItem(String description) {
        return this.tx(session -> {
            List<Item> items = session.createQuery("from Item where description=:description")
                                    .setParameter("description", description)
                                    .list();
            return (items.size() > 0 ? items.get(0) : null);
        });
    }

    @Override
    public List<Item> allItems() {
        return this.tx(session -> session.createQuery("from Item order by created").list());
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = factory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T result = command.apply(session);
            tx.commit();
            return result;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
