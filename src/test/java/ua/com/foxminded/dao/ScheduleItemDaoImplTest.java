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
    private String teacherUUID = "95d5a598-4fa1-4937-acf7-382a878d19fa";
    private ScheduleItem scheduleItem;
    private ScheduleItem scheduleItemExpected;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    FileParser file;

    @Autowired
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
                                         .setTeacher(new Teacher().setIdTeacher(teacherUUID));

        scheduleItemExpected = new ScheduleItem().setDayOfWeek("MONDAY")
                                                 .setGroup(new Group().setName("gr-1"))
                                                 .setRoom(new Room().setName("room 1"))
                                                 .setSubject(new Subject().setName("Maths"))
                                                 .setTimeSlot(new TimeSlot().setSerialNumber(1));

    }

    @Test
    @Order(1)
    void addScheduleItem_shouldReturnScheduleItem_whenAddScheduleItem() {
        List<ScheduleItem> expected = new ArrayList<>();
        expected.add(scheduleItemExpected);

        scheduleItemDao.addScheduleItem(scheduleItem);
        List<ScheduleItem> actual = scheduleItemDao.findScheduleTeacher("Chavan");
        assertEquals(expected, actual);
    }

    @Test
    @Order(2)
    void findScheduleTeacher_shouldReturnScheduleTeacher_whenLookForLastName() {
        List<ScheduleItem> expected = new ArrayList<>();

        expected.add(scheduleItemExpected);

        List<ScheduleItem> actual = scheduleItemDao.findScheduleTeacher("Chavan");

        assertEquals(expected, actual);

    }

    @Test
    @Order(2)
    void findScheduleTeacher_shouldReturnEmpty_whenLookForNonExistentTeacher() {

        List<Student> expected = new ArrayList<>();

        List<ScheduleItem> actual = scheduleItemDao.findScheduleTeacher("Chavan99");
        assertEquals(expected, actual);
    }

    @Test
    @Order(3)
    void findScheduleStudent_shouldReturnScheduleStudent_whenLookForLastName() {
        List<ScheduleItem> expected = new ArrayList<>();

        expected.add(scheduleItemExpected);

        List<ScheduleItem> actual = scheduleItemDao.findScheduleStudent("Svitlychna");

        assertEquals(expected.get(0), actual.get(2));
    }

    @Test
    @Order(4)
    void editScheduleItem_shouldReturnDayOfWeek_whenEditDayOfWeek() {

        scheduleItemDao.editScheduleItem(scheduleItem.setDayOfWeek("WENDNESDAY"));
        List<ScheduleItem> actual = scheduleItemDao.findScheduleTeacher("Chavan");
        assertEquals("WENDNESDAY", actual.get(0).getDayOfWeek());
    }

//    @Test
//    @Order(5)
//    void deleteScheduleItem_shouldReturnEmpty_whenDeleteScheduleItem() {
//        List<ScheduleItem> expected = new ArrayList<>();
//        scheduleItemDao.deleteScheduleItem(scheduleItemUUID);
//        List<ScheduleItem> actual = scheduleItemDao.findScheduleTeacher("Chavan");
//        assertEquals(expected, actual);
//    }

}
