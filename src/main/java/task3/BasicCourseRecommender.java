package task3;

import Controller.Course;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Component("basicCourseRecommender")
public class BasicCourseRecommender implements CourseRecommender {
    public List<Course> recommendedCourses() {
        return Arrays.asList(
                new Course("Introduction to Programming"),
                new Course("Basic Data Structures")
        );
    }
}

