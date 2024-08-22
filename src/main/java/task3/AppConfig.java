package task3;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;


@Configuration
@ComponentScan(basePackages = {"task3","Controller","Repositories","Services","Mappers"})
public class AppConfig {
}
