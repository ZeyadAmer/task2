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
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/courses");
        dataSource.setUsername("root");
        dataSource.setPassword("@MindGame@28");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}