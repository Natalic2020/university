package ua.com.foxminded.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ua.com.foxminded.config.ScheduleItemDaoImplTestConfiguration;
import ua.com.foxminded.dao.entity.Group;
import ua.com.foxminded.dao.entity.Room;
import ua.com.foxminded.dao.entity.ScheduleItem;
import ua.com.foxminded.dao.entity.Subject;
import ua.com.foxminded.dao.entity.TimeSlot;
import ua.com.foxminded.model.dto.ScheduleItemDto;
import ua.com.foxminded.service.interfaces.ScheduleService;


@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ScheduleItemDaoImplTestConfiguration.class })
@TestInstance(Lifecycle.PER_CLASS)
//@ContextConfiguration(classes = { ApplicationConfigTest.class })
//@SpringApplicationConfiguration(classes = MocksApplication.class)
class ScheduleServiceImplTest {

    @Autowired
    ScheduleService scheduleService;
    
    @Autowired
    ScheduleItemDaoImpl scheduleItemDaoImpl;
  
    List<ScheduleItem> scheduleItems;
    
    @BeforeAll
    void setUpBeforeClass() throws Exception {
        MockitoAnnotations.initMocks(this);
        scheduleItems = new ArrayList<>();
        scheduleItems.add(new ScheduleItem().setDayOfWeek("THUESDAY")
                .setGroup(new Group().setName("gr-1"))
                .setRoom(new Room().setName("room 1"))
                .setSubject(new Subject().setName("Maths"))
                .setTimeSlot(new TimeSlot().setSerialNumber(1)));
    }

    @Test
    void test() {
//        ScheduleItemDaoImpl   scheduleItemDaoImpl =  Mockito.mock(ScheduleItemDaoImpl.class);
        
        Mockito.when(scheduleItemDaoImpl.findWeekScheduleStudent(UUID.randomUUID())).thenReturn(scheduleItems);
        
        List<ScheduleItemDto> scheduleItem  = scheduleService.findWeekScheduleStudent(UUID.randomUUID());
        Map<String, List<ScheduleItemDto>> schedule = scheduleService.findMonthScheduleStudent(UUID.fromString("a17f83c5-a85a-4420-8423-23b86d0463c6"), LocalDate.of(1999, 02, 02));
       
        
//        assertEquals(expected, "");
    }

    @Test
    void test2() {

        List<ScheduleItem> expected = new ArrayList<>();

        Map<String, List<ScheduleItemDto>> schedule = scheduleService.findMonthScheduleStudent(
                UUID.fromString("a17f83c5-a85a-4420-8423-23b86d0463c6"), LocalDate.of(1999, 02, 02));

        assertEquals(expected, "");
    }

}
