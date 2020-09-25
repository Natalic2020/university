package ua.com.foxminded.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import ua.com.foxminded.dao.ScheduleItemDaoImpl;

@Profile("test")
@Configuration
@ComponentScan(basePackages = "ua.com.foxminded.service")
@ComponentScan(basePackages = "ua.com.foxminded.converter")
public class ScheduleItemDaoImplTestConfiguration {

    @Bean
    @Primary
    public  ScheduleItemDaoImpl  scheduleItemDaoImpl() {
        return Mockito.mock( ScheduleItemDaoImpl.class);
    }
}
