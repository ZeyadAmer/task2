package task3;
import Controller.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import task2.CourseRecommender;

import java.util.List;

@Service
public class CourseService {
    private CourseRecommender courseRecommender;


    @Autowired
    public CourseService(@Qualifier("advancedOverride")CourseRecommender courseRecommender) {
        this.courseRecommender = courseRecommender;
    }


    public List<Course> getRecommendedCourses() {

        return courseRecommender.recommendedCourses();
    }




}
