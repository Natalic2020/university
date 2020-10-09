package ua.com.foxminded.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import ua.com.foxminded.dao.TablesInitializer;

@Component
public class AppSpringEventListener  {

  @Autowired
  TablesInitializer tableInitializer;
   
        @EventListener({ContextRefreshedEvent.class})
        void contextRefreshedEvent() {
            System.out.println("a context refreshed event happened");
            tableInitializer.createTables();
            tableInitializer.fillTables();
        }   
}
                                                                                                   