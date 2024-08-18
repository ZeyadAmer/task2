package task3;

import Controller.Course;
import Controller.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


public class dbCourseRecommender implements CourseRecommender {

    @Autowired
    CourseRepository courseRepository;

    public List<Course> recommendedCourses() {
        return courseRepository.getRecommendedCourses();
    }
}
