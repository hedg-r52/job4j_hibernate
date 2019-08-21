package models;

import org.junit.Test;
import java.sql.Timestamp;
import static org.junit.Assert.*;

/**
 * Test for class User
 * @author Andrei Soloviev (hedg.r52@gmail.com)
 * @since 21.08.2019
 */
public class UserTest {

    @Test
    public void whenUsersHaveSameFieldsThenUserEquals() {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        User user1 = new User();
        user1.setId(1);
        user1.setName("Covax");
        user1.setExpired(time);
        User user2 = new User();
        user2.setId(1);
        user2.setName("Covax");
        user2.setExpired(time);
        assertEquals(user1, user2);
    }

    @Test
    public void whenUsersHaveDifferentFieldsThenUserNotEquals() {
        Timestamp time = new Timestamp(System.currentTimeMillis());
        User user1 = new User();
        user1.setId(1);
        user1.setName("Covax");
        user1.setExpired(time);
        User user2 = new User();
        user2.setId(2);
        user2.setName("Sylvia");
        user2.setExpired(time);
        assertNotEquals(user1, user2);
    }
}
