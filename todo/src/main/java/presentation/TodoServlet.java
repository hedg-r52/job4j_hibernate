package presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import logic.ItemService;
import logic.Service;
import models.Item;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class TodoServlet extends HttpServlet {
    private final Service service = ItemService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json");
        PrintWriter pw = resp.getWriter();
        List<Item> items = service.allItems();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(pw, items);
        pw.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json");
        try {
            JSONObject json = (JSONObject) new JSONParser().parse(req.getReader());
            service.action((String) json.get("action"), json);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
