package task3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import task2.CourseRecommender;

@Configuration
@ComponentScan(basePackages = {"task3","task2"})
public class AppConfig {

}
