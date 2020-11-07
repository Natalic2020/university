import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@ServletComponentScan
@ComponentScan(basePackages = "ua.com.foxminded")
@EnableJpaRepositories(basePackages = "ua.com.foxminded.dao")
@EntityScan(basePackages = "ua.com.foxminded.dao.entity")
public class Application extends SpringBootServletInitializer{
    
    public static void main(String[] args) {     
        SpringApplication.run(Application.class, args);
    }
}
