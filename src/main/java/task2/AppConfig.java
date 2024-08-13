package task2;

import org.springframework.context.annotation.*;
import task3.CourseRecommender;

@Configuration
@ImportResource("classpath:beans.xml")
@ComponentScan(basePackages = "task2")


public class AppConfig {

    @Bean(name="advancedCourseRecommender")
    public CourseRecommender advancedCourseRecommender() {
        return new AdvancedCourseRecommender();
    }
}