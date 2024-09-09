package task3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import Controller.*;
import task2.CourseRecommender;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Service
public class CourseService {
    private CourseRecommender courseRecommender;
    private Connection connection;


    @Autowired
    public CourseService(@Qualifier("basicCourseRecommender") CourseRecommender courseRecommender,
                         @Qualifier("CoursesConnection") DataBaseConnection dataBaseConnection) throws SQLException {
        this.courseRecommender = courseRecommender;
        this.connection = dataBaseConnection.getConnection();
        if (connection != null) {
            System.out.println("Connected to the database!");
        }
    }

    public void addCourse(String name, String description, int credit) {
        String sql = "INSERT INTO course (name, description, credit) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setInt(3, credit);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCourse(int id, String name, String description, int credit) {
        String sql = "UPDATE course SET name = ?, description = ?, credit = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setInt(3, credit);
            ps.setInt(4, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Course viewCourse(int id) {
        String sql = "SELECT * FROM course WHERE id = ?";
        Course course = null;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    course = new Course(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getInt("credit")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }

    public void deleteCourse(int id) {
        String sql = "DELETE FROM course WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Course> viewAllCourses() {
        String sql = "SELECT * FROM course";
        List<Course> courses = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Course course = new Course(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("credit")
                );
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

}