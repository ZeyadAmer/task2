package task2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import task3.*;
import Controller.*;
import java.sql.Connection;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;



@Service
public class CourseService {
    private CourseRecommender courseRecommender;
    private JdbcTemplate jdbcTemplate;
    private Connection connection;


    @Autowired
    public CourseService(@Qualifier("basicCourseRecommender")CourseRecommender courseRecommender,
                         JdbcTemplate jdbcTemplate) {
        this.courseRecommender = courseRecommender;
        this.jdbcTemplate = jdbcTemplate;

    }

    public void addCourse(String name, String description, int credit) {
        String sql = "INSERT INTO course (name, description, credit) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, name, description, credit);
    }

    public void updateCourse(int id, String name, String description, int credit) {
        String sql = "UPDATE course SET name = ?, description = ?, credit = ? WHERE id = ?";
        jdbcTemplate.update(sql, name, description, credit, id);
    }

    public Course viewCourse(int id) {
        String sql = "SELECT * FROM course WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new CourseRowMapper());
    }

    public void deleteCourse(int id) {
        String sql = "DELETE FROM course WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<Course> viewAllCourses() {
        String sql = "SELECT * FROM course";
        return jdbcTemplate.query(sql, new CourseRowMapper());
    }

    public List<Course> getRecommendedCourses() {

        return courseRecommender.recommendedCourses();
    }




}
