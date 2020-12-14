package ua.com.foxminded.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
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
//@SpringJUnitConfig(ScheduleItemDaoImplTestConfiguration.class)
@Disabled
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

//        scheduleItemDao = new ScheduleItemDaoImplTestConfiguration().scheduleItemDao();

    }
}
