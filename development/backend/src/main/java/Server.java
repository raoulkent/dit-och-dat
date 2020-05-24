import com.google.gson.Gson;
import model.Course;
import model.Hole;
import model.Tee;
import org.sql2o.Sql2o;

import java.util.ArrayList;
import java.util.List;

import static spark.Spark.*;

public class Server {
    public static void main(String[] args) {
        Gson gson = new Gson();

        List<Hole> holes = new ArrayList<>();
        List<Tee> tees = new ArrayList<>();

        holes.add(new Hole(1, 4, 3));
        holes.add(new Hole(2, 5, 1));

        tees.add(new Tee("Röd - Dam", 33.0, 22.0));

        Course course = new Course("Chalmers GK", holes, tees);

        Sql2o sql2o = new Sql2o("jdbc:mysql://127.0.0.1:3306/simplegolf", "simplegolf", "xxx");
        CourseDAO courseDAO = new CourseDAO(sql2o);

        System.out.println("Starting server...");
        port(6543);
        get("/course", (req, res) -> {
            res.type("application/json");
            return courseDAO.getAll();
        }, gson::toJson);
    }
}
