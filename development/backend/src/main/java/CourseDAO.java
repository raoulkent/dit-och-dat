import model.Course;
import model.Hole;
import model.Tee;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    private Sql2o sql2o;

    public CourseDAO(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public String getData() {
        try (Connection con = sql2o.open()) {
            final String query = "SELECT column_2 FROM test_table LIMIT 1";
            String result = con.createQuery(query).executeScalar(String.class);
            return result;
        }
    }

    public List<Course> getAll() {
        try (Connection con = sql2o.open()) {
            final String queryCourseNames = "SELECT name FROM hole GROUP BY name";
            List<String> courseNames = con.createQuery(queryCourseNames).executeAndFetch(String.class);

            List<Course> courses = new ArrayList<>();
            for (String name : courseNames) {
                final String queryHoles = "SELECT holeNumber, par, hcpIndex FROM hole WHERE name = :name";
                List<Hole> holes = con.createQuery(queryHoles)
                        .addParameter("name", name)
                        .executeAndFetch(Hole.class);

                final String queryTees = "SELECT teeName AS name, courseRating, slopeRating FROM tee WHERE courseName = :name";
                List<Tee> tees = con.createQuery(queryTees)
                        .addParameter("name", name)
                        .executeAndFetch(Tee.class);

                courses.add(new Course(name, holes, tees));
            }
            return courses;
        }
    }
}
