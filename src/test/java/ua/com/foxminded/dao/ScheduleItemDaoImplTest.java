package ua.com.foxminded.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ua.com.foxminded.config.ApplicationConfigTest;
import ua.com.foxminded.dao.entity.Group;
import ua.com.foxminded.dao.entity.Room;
import ua.com.foxminded.dao.entity.ScheduleItem;
import ua.com.foxminded.dao.entity.Student;
import ua.com.foxminded.dao.entity.Subject;
import ua.com.foxminded.dao.entity.Teacher;
import ua.com.foxminded.dao.entity.TimeSlot;
import ua.com.foxminded.dao.interfaces.ScheduleItemDao;
import ua.com.foxminded.util.FileParser;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ApplicationConfigTest.class })
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
class ScheduleItemDaoImplTest {

    private String scheduleItemUUID = "8f240794-fb1e-11ea-adc1-0242ac120002";
    private String groupUUID = "0c149265-57c0-4942-a1e5-06c8b6983a23";
    private String subjectUUID = "c4320591-215a-4b11-958e-9864c184ec2a";
    private String roomUUID = "026621cc-73a6-40ba-8ea7-86628f4cb802";
    private String timeSlotUUID = "d789bb56-b534-402b-8baa-38547218761c";
    private String dayOfWeek = "MONDAY";
    private String teacherUUID = "69c97b95-8ce5-4923-8d39-4c17bd5389d0";
    private ScheduleItem scheduleItem;
    private ScheduleItem scheduleItemExpected;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    FileParser file;

    @Autowired
    @Qualifier("scheduleItemDao")
    ScheduleItemDao scheduleItemDao;

    @BeforeAll
    void setUpBeforeClass() throws Exception {
        jdbcTemplate.batchUpdate(file.readFileToLines("sql_test.script"));
        jdbcTemplate.batchUpdate(file.readFileToLines("tables_test.script"));
        scheduleItem = new ScheduleItem()
                                         .setId(scheduleItemUUID)
                                         .setGroup(new Group().setId(groupUUID))
                                         .setSubject(new Subject().setId(subjectUUID))
                                         .setRoom(new Room().setId(roomUUID))
                                         .setTimeSlot(new TimeSlot().setId(timeSlotUUID))
                                         .setDayOfWeek(dayOfWeek)
                                         .setTeacher((Teacher) new Teacher().setIdPerson(teacherUUID));

        scheduleItemExpected = new ScheduleItem().setDayOfWeek("MONDAY")
                                                 .setGroup(new Group().setName("gr-1"))
                                                 .setRoom(new Room().setName("room 1"))
                                                 .setSubject(new Subject().setName("Maths"))
                                                 .setTimeSlot(new TimeSlot().setSerialNumber(1));

    }
}
