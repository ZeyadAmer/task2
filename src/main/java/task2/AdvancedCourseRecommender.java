package task2;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;
import task3.CourseRecommender;
import task3.Course;

@Component("advancedCourseRecommender")
public class AdvancedCourseRecommender implements CourseRecommender {
    @Override
    public List<Course> recommendedCourses() {
        return Arrays.asList(
                new Course("Advanced Algorithms"),
                new Course("Design Patterns in Java")
        );
    }
}

