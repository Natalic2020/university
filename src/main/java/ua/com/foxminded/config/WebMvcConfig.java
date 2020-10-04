package ua.com.foxminded.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("ua.com.foxminded")
public class WebMvcConfig {
    Logger logger = LoggerFactory.getLogger("SampleLogger");
    
    @Bean(name = "viewResolver")
    public InternalResourceViewResolver getViewResolver() {
        logger.info("-------------------------WebMvcConfig  getViewResolver");
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}