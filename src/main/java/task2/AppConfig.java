package task2;

import org.springframework.context.annotation.*;
import task3.CourseRecommender;

@Configuration
@ComponentScan(basePackages = {"task2","task3"})



public class AppConfig {

    @Bean(name="advancedCourseRecommender")
    public CourseRecommender advancedCourseRecommender() {
        return new AdvancedCourseRecommender();
    }

    @Bean(name="basicCourseRecommender")
    public CourseRecommender basicCourseRecommender() {
        return new BasicOverride();
    }
}