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
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import ua.com.foxminded.config.ScheduleItemDaoImplTestConfiguration;
import ua.com.foxminded.dao.entity.Group;
import ua.com.foxminded.dao.entity.Room;
import ua.com.foxminded.dao.entity.ScheduleItem;
import ua.com.foxminded.dao.entity.Subject;
import ua.com.foxminded.dao.entity.TimeSlot;
import ua.com.foxminded.dao.interfaces.ScheduleItemDao;
import ua.com.foxminded.model.dto.GroupDto;
import ua.com.foxminded.model.dto.RoomDto;
import ua.com.foxminded.model.dto.ScheduleItemDto;
import ua.com.foxminded.model.dto.SubjectDto;
import ua.com.foxminded.model.dto.TimeSlotDto;
import ua.com.foxminded.model.enums.DayOfWeek;
import ua.com.foxminded.service.ScheduleServiceImpl;
import ua.com.foxminded.service.interfaces.ScheduleService;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
@SpringJUnitConfig(ScheduleItemDaoImplTestConfiguration.class)
class ScheduleServiceImplTest {

    ScheduleService scheduleService;

    static ScheduleItemDao scheduleItemDao;

    static List<ScheduleItem> scheduleItems;

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {

        scheduleItems = new ArrayList<>();
        scheduleItems.add(new ScheduleItem().setDayOfWeek("TUESDAY")
                                            .setGroup(new Group().setName("gr-1"))
                                            .setRoom(new Room().setName("room 1"))
                                            .setSubject(new Subject().setName("Maths"))
                                            .setTimeSlot(new TimeSlot().setSerialNumber(1)));

        scheduleItemDao = new ScheduleItemDaoImplTestConfiguration().scheduleItemDao();

    }

    @Test
    void findWeekcheduleStudent_shoudReturnScheduleItemWeek_whenLookForWeekScheduleStudent() {

        List<ScheduleItemDto> expected = new ArrayList<>();

        expected.add(new ScheduleItemDto().setDayOfWeek(DayOfWeek.TUESDAY)
                                          .setGroup(new GroupDto().setName("gr-1"))
                                          .setRoom(new RoomDto().setName("room 1"))
                                          .setSubject(new SubjectDto().setName("Maths"))
                                          .setTimeSlot(new TimeSlotDto().setSerialNumber(1)));

        String uuid1 = "a17f83c5-a85a-4420-8423-23b86d0463c6";

        Mockito.when(scheduleItemDao.findWeekScheduleStudent(uuid1)).thenReturn(scheduleItems);

        scheduleService = new ScheduleServiceImpl(scheduleItemDao);

        List<ScheduleItemDto> actual = scheduleService.findWeekScheduleStudent(UUID.fromString(uuid1));
        assertEquals(expected, actual);

    }

    @Test
    void findMonthcheduleStudent_shoudReturnScheduleItemMonth_whenLookForMonthScheduleStudent() {

        List<ScheduleItemDto> expected = new ArrayList<>();

        expected.add(new ScheduleItemDto().setDayOfWeek(DayOfWeek.TUESDAY)
                                          .setGroup(new GroupDto().setName("gr-1"))
                                          .setRoom(new RoomDto().setName("room 1"))
                                          .setSubject(new SubjectDto().setName("Maths"))
                                          .setTimeSlot(new TimeSlotDto().setSerialNumber(1)));

        String uuid1 = "a17f83c5-a85a-4420-8423-23b86d0463c6";

        Mockito.when(scheduleItemDao.findWeekScheduleStudent(uuid1)).thenReturn(scheduleItems);

        scheduleService = new ScheduleServiceImpl(scheduleItemDao);

        Map<String, List<ScheduleItemDto>> actual = scheduleService.findMonthScheduleStudent(UUID.fromString(uuid1),
                                                                           LocalDate.of(1999, 02, 02));

        assertEquals(expected, actual.get("1999-02-09"));
    }

    @Test
    void findWeekScheduleTeacher_shoudReturnScheduleItemWeek_whenLookForWeekScheduleTeacher() {

        List<ScheduleItemDto> expected = new ArrayList<>();

        expected.add(new ScheduleItemDto().setDayOfWeek(DayOfWeek.TUESDAY)
                                          .setGroup(new GroupDto().setName("gr-1"))
                                          .setRoom(new RoomDto().setName("room 1"))
                                          .setSubject(new SubjectDto().setName("Maths"))
                                          .setTimeSlot(new TimeSlotDto().setSerialNumber(1)));

        String uuid1 = "a17f83c5-a85a-4420-8423-23b86d0463c6";

        Mockito.when(scheduleItemDao.findWeekScheduleStudent(uuid1)).thenReturn(scheduleItems);

        scheduleService = new ScheduleServiceImpl(scheduleItemDao);

        List<ScheduleItemDto> actual = scheduleService.findWeekScheduleTeacher(UUID.fromString(uuid1));
        assertEquals(expected, actual);

    }

    @Test
    void findMonthcheduleTeacher_shoudReturnScheduleItemMonth_whenLookForMonthScheduleTeacher() {

        List<ScheduleItemDto> expected = new ArrayList<>();

        expected.add(new ScheduleItemDto().setDayOfWeek(DayOfWeek.TUESDAY)
                                          .setGroup(new GroupDto().setName("gr-1"))
                                          .setRoom(new RoomDto().setName("room 1"))
                                          .setSubject(new SubjectDto().setName("Maths"))
                                          .setTimeSlot(new TimeSlotDto().setSerialNumber(1)));

        String uuid1 = "a17f83c5-a85a-4420-8423-23b86d0463c6";

        Mockito.when(scheduleItemDao.findWeekScheduleTeacher(uuid1)).thenReturn(scheduleItems);

        scheduleService = new ScheduleServiceImpl(scheduleItemDao);

        Map<String, List<ScheduleItemDto>> actual = scheduleService.findMonthScheduleTeacher(UUID.fromString(uuid1),
                                                                          LocalDate.of(1999, 02, 02));

        assertEquals(expected, actual.get("1999-02-09"));
    }

}
