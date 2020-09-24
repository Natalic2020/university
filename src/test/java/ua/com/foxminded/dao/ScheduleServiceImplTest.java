package ua.com.foxminded.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ua.com.foxminded.config.ApplicationConfigTest;
import ua.com.foxminded.dao.entity.Group;
import ua.com.foxminded.dao.entity.Room;
import ua.com.foxminded.dao.entity.ScheduleItem;
import ua.com.foxminded.dao.entity.Subject;
import ua.com.foxminded.dao.entity.Teacher;
import ua.com.foxminded.dao.entity.TimeSlot;
import ua.com.foxminded.dao.interfaces.ScheduleItemDao;
import ua.com.foxminded.model.dto.ScheduleItemDto;
import ua.com.foxminded.service.interfaces.ScheduleItemService;
import ua.com.foxminded.service.interfaces.ScheduleService;
import ua.com.foxminded.util.FileParser;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ApplicationConfigTest.class })
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
class ScheduleServiceImplTest {

//    @Autowired
//    JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    FileParser file;

    @Autowired
    ScheduleService scheduleService;

    ScheduleItemDaoImpl scheduleItemDaoImpl;
//    QualificationReport racersList;

    @BeforeAll
    void setUpBeforeClass() throws Exception {
//        jdbcTemplate.batchUpdate(file.readFileToLines("sql_test.script"));
//        jdbcTemplate.batchUpdate(file.readFileToLines("tables_test.script"));

    }

    @Test
    void test() {
        scheduleItemDaoImpl =  Mockito.mock(ScheduleItemDaoImpl.class);
        
        List<ScheduleItem> scheduleItems = new ArrayList<>();
        scheduleItems.add(new ScheduleItem().setDayOfWeek("THUESDAY")
                .setGroup(new Group().setName("gr-1"))
                .setRoom(new Room().setName("room 1"))
                .setSubject(new Subject().setName("Maths"))
                .setTimeSlot(new TimeSlot().setSerialNumber(1)));
        
        Mockito.when(scheduleItemDaoImpl.findWeekScheduleStudent(UUID.randomUUID())).thenReturn(scheduleItems);
        
        
        Map<String, List<ScheduleItemDto>> schedule = scheduleService.findMonthScheduleStudent(UUID.fromString("a17f83c5-a85a-4420-8423-23b86d0463c6"), LocalDate.of(1999, 02, 02));
        List<ScheduleItemDto> scheduleItem  = scheduleService.findWeekScheduleStudent(UUID.randomUUID());
        
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
