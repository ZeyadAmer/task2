package task3;

import org.springframework.context.annotation.*;



@Configuration
@ComponentScan(basePackages = {"task3","Controller"})

public class AppConfig {
    @Bean(name="basicCourseRecommender")
    public CourseRecommender basicCourseRecommender() {
        return new BasicCourseRecommender();
    }




}
