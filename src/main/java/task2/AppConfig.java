package task2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackages = "task2")
public class AppConfig {

    @Bean(name = "basicCourseRecommender")

    public CourseRecommender basicCourseRecommender() {
        return new BasicCourseRecommender();
    }

    @Bean(name = "advancedCourseRecommender")
    @Primary
    public CourseRecommender advancedCourseRecommender() {
        return new AdvancedCourseRecommender();
    }
}