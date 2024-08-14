package task2;
import Controller.Course;
import org.springframework.stereotype.Component;
import task3.CourseRecommender;
import java.util.Arrays;
import java.util.List;

@Component
public class BasicOverride implements CourseRecommender {
    @Override
    public List<Course> recommendedCourses() {
        return Arrays.asList(
                new Course("basic"),
                new Course("overwritten")
        );
    }
}