package logic;

import models.Item;
import org.json.simple.JSONObject;
import persistent.DBStore;
import persistent.Store;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ItemService implements Service {
    private final static ItemService INSTANCE = new ItemService();
    private final Store store = DBStore.getInstance();
    private final Map<String, Consumer<JSONObject>> dispatch = new HashMap<>();

    public ItemService() {
        init();
    }

    @Override
    public void action(final String param, final JSONObject jsonObject) {
        if (this.dispatch.keySet().contains(param)) {
            this.dispatch.get(param).accept(jsonObject);
        }
    }

    public static ItemService getInstance() {
        return INSTANCE;
    }

    @Override
    public long add(Item item) {
        return store.add(item);
    }

    @Override
    public void update(Item item) {
        store.update(item);
    }

    @Override
    public void delete(Item item) {
        store.delete(item);
    }

    @Override
    public Item findItem(long id) {
        return store.findItem(id);
    }

    @Override
    public Item findItem(String description) {
        return store.findItem(description);
    }

    @Override
    public List<Item> allItems() {
        return store.allItems();
    }

    private void init() {
        this.dispatch.put("add", this::handleAdd);
        this.dispatch.put("update", this::handleUpdate);
        this.dispatch.put("delete", this::handleDelete);
    }

    private void handleDelete(JSONObject jsonObject) {
        Item item = new Item();
        item.setId((Long) jsonObject.get("id"));
        delete(item);
    }

    private void handleUpdate(JSONObject jsonObject) {
        Long id = (Long) jsonObject.get("id");
        Item item = findItem(id);
        item.setDone(!item.isDone());
        update(item);
    }

    private void handleAdd(JSONObject jsonObject) {
        Item item = new Item();
        item.setDescription((String) jsonObject.get("description"));
        add(item);
    }
}
