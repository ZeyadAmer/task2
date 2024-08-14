package task2;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import task3.CourseRecommender;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = {"task2","task3","Controller"})



public class AppConfig {

    @Bean(name="advancedCourseRecommender")
    @Primary
    public CourseRecommender advancedCourseRecommender() {
        return new AdvancedCourseRecommender();
    }

    @Bean(name="basicCourseRecommender")
    public CourseRecommender basicCourseRecommender() {
        return new BasicOverride();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}