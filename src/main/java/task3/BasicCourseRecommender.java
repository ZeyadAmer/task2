package task3;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;

@Component("basicCourseRecommender")
public class BasicCourseRecommender implements CourseRecommender {
    @Override
    public List<Course> recommendedCourses() {
        return Arrays.asList(
                new Course("Introduction to Programming"),
                new Course("Basic Data Structures")
        );
    }
}

