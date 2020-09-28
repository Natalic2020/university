package ua.com.foxminded.config;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import ua.com.foxminded.dao.ScheduleItemDaoImpl;
import ua.com.foxminded.dao.interfaces.ScheduleItemDao;

@Profile("test")
@Configuration
public class ScheduleItemDaoImplTestConfiguration {

    @Bean
    @Qualifier("scheduleItemDao")
    public ScheduleItemDao scheduleItemDao() {
        return Mockito.mock( ScheduleItemDaoImpl.class);
    }
}
