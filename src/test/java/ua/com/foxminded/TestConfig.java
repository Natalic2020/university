package ua.com.foxminded;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import ua.com.foxminded.dao.interfaces.ScheduleItemDao;
import ua.com.foxminded.service.ScheduleItemServiceImpl;

@Profile("test")
@Configuration
public class TestConfig {
//    @Bean
//    public JavaMailSender mailSender() {
//       // example of returning a mock object 
//       return Mockito.mock(JavaMailSender.class);
//    }    
//
//    @Bean
//    public GreenMail smtpServer() {
//       // another mock
//       return Mockito.mock(GreenMail.class);
//    }

    @Bean
    @Qualifier("scheduleItemDao")
    public ScheduleItemDao scheduleItemDao() {
        return Mockito.mock( ScheduleItemDao.class);
    }

    
    @Bean
    public ScheduleItemServiceImpl scheduleItemServiceImple(){
        // this could also be used to return a Mock object
        return new ScheduleItemServiceImpl();
    }
}