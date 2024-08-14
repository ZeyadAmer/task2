package task3;

import Controller.CoursesConnection;
import org.springframework.context.annotation.*;


@Configuration
@ComponentScan(basePackages = {"task3","Controller"})

public class AppConfig {
    @Bean(name="basicCourseRecommender")
    public CourseRecommender basicCourseRecommender() {
        return new BasicCourseRecommender();
    }

    @Bean(name="CoursesConnection")
    public CoursesConnection coursesConnection() {
        return new CoursesConnection();
    }
}
