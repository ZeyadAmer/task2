package task3.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import task3.Repositories.CourseRepository;
import Controller.*;

@Configuration
public class Discover {
    private final CourseRepository courseRepository;

    public Discover(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Bean(name = "courseWithLowestCredit")
    public Course discover() {
        return courseRepository.findFirstByOrderByCreditAsc()
                .orElseThrow(() -> new RuntimeException("No course found with the lowest credit"));
    }
}
