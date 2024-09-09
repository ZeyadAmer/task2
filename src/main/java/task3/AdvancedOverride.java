package task3;

import Controller.Course;
import org.springframework.stereotype.Component;
import task2.AdvancedCourseRecommender;


import java.util.Arrays;
import java.util.List;

@Component("advancedOverride")
public class AdvancedOverride extends AdvancedCourseRecommender {
    @Override
    public List<Course> recommendedCourses() {
        return Arrays.asList(
                new Course("Advanced "),
                new Course("Overwritten")
        );
    }
}

