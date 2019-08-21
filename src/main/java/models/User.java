package models;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * class User
 * @author Andrei Soloviev (hedg.r52@gmail.com)
 * @since 21.08.2019
 */
public class User {
    private int id;
    private String name;
    private Timestamp expired;

    public User() { }

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

    public Timestamp getExpired() {
        return expired;
    }

    public void setExpired(Timestamp expired) {
        this.expired = expired;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(expired, user.expired);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, expired);
    }
}
