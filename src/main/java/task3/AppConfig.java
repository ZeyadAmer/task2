package task3;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;


@Configuration
@ComponentScan(basePackages = {"task3","Controller","Repositories","Services","Mappers"})
public class AppConfig {
    @Bean(name="basicCourseRecommender")
    public CourseRecommender basicCourseRecommender() {
        return new BasicCourseRecommender();
    }

//    @Bean(name="dbCourseRecommender")
//    public CourseRecommender dbCourseRecommender() {
//        return new dbCourseRecommender();
//    }


    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
