package logic;

import models.Item;
import org.json.simple.JSONObject;
import java.util.List;

public interface Service {
    long add(Item item);
    void update(Item item);
    void delete(Item item);
    Item findItem(long id);
    Item findItem(String description);
    List<Item> allItems();
    void action(String action, JSONObject json);
}
