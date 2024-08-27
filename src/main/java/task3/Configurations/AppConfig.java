package task3.Configurations;

import org.springframework.context.annotation.*;


@Configuration
@ComponentScan(basePackages = {"task3","Controller","Repositories","Services","Mappers"})
public class AppConfig {
}
