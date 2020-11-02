package ua.com.foxminded.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@SpringBootApplication
@EnableAutoConfiguration
//(exclude = { MySpringMvcDispatcherSerlvetIntitializer.class })
@ServletComponentScan
@ComponentScan(basePackages = "ua.com.foxminded")
public class Application extends SpringBootServletInitializer{

    @Autowired
    private Environment env;
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

//    @Bean(name = "dataSource")
//    public DataSource getDataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
// 
//        // See: application.properties
//        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
//        dataSource.setUrl(env.getProperty("spring.datasource.url"));
//        dataSource.setUsername(env.getProperty("spring.datasource.username"));
//        dataSource.setPassword(env.getProperty("spring.datasource.password"));
// 
//        System.out.println("## getDataSource: " + dataSource);
// 
//        return dataSource;
//    }   
}
