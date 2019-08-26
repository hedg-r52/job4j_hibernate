package persistent;

import models.Item;
import java.util.List;

public interface Store {
    long add(Item item);
    void update(Item item);
    void delete(Item item);
    Item findItem(long id);
    Item findItem(String description);
    List<Item> allItems();
}
