package ua.com.foxminded.config;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StudentSpringEventListener  {

        @EventListener({ContextRefreshedEvent.class})
        void contextRefreshedEvent() {
            System.out.println("a context refreshed event happened");
        }   
}
                                                                                                   