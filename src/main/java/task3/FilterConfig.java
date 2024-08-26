package task3;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<SecurityFilter> securityFilter() {
        FilterRegistrationBean<SecurityFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new SecurityFilter());
        registrationBean.addUrlPatterns("/courses/add", "/courses/update", "/courses/delete");

        return registrationBean;
    }
}
