package ua.com.foxminded.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import ua.com.foxminded.config.ApplicationConfig;
import ua.com.foxminded.dao.TablesInitializer;
import ua.com.foxminded.model.dto.GroupDto;
import ua.com.foxminded.model.dto.PeriodDto;
import ua.com.foxminded.model.dto.RoomDto;
import ua.com.foxminded.model.dto.ScheduleItemDto;
import ua.com.foxminded.model.dto.StudentDto;
import ua.com.foxminded.model.dto.SubjectDto;
import ua.com.foxminded.model.dto.TeacherDto;
import ua.com.foxminded.model.dto.TimeSlotDto;
import ua.com.foxminded.model.dto.WeekScheduleDto;
import ua.com.foxminded.model.enums.DayOfWeek;
import ua.com.foxminded.model.enums.Degree;
import ua.com.foxminded.model.enums.Department;
import ua.com.foxminded.model.enums.Specialty;
import ua.com.foxminded.model.enums.StudyStatus;

public class ScheduleGenerator {

    
    public void fillSchedule(AbstractApplicationContext context) {

        ScheduleService scheduleService = (ScheduleService) context.getBean("scheduleService");
        createSchedule(scheduleService);
        
        
//        List<ScheduleItemDto> schedule = scheduleService.findScheduleTeacher("Ivaniv", LocalDate.of(2019, 9, 2));
//        schedule.forEach(e -> System.out.println(e.toString()));  
    }
    
    
    
    public void createSchedule(ScheduleService scheduleService){
        createSchedule2().forEach(element -> {
            scheduleService.addSchedule(createScheduleSmall(element));
        });
    }
    
    public List <Map<String, Object>> createSchedule2(){
        List <Map<String, Object>> shedule = new ArrayList();
        
        Map<String, Object> mapValue = new HashMap<>();
        List<String> students = new ArrayList<>();
        students.add("NataSwitlychna");
        students.add("KataLoza");
        students.add("NinaVano");
        mapValue.put("students", students);
        List<String> teachers = new ArrayList<>();
        teachers.add("LolaIvaniv");
        teachers.add("NadaPetrov");
        teachers.add("IgorKoko");
        mapValue.put("teachers", teachers);
        mapValue.put("group", "gr_2");
        mapValue.put("room", "room 2");
        mapValue.put("subject", "Maths");
        mapValue.put("serialNumber", 2);
        mapValue.put("dayOfweek", DayOfWeek.MONDAY);
        shedule.add(mapValue);
        
        Map<String, Object> mapValue2 = new HashMap<>();
        List<String> students2 = new ArrayList<>();
        students2.add("NataSvitlychna");
        students2.add("KataLoza");
        students2.add("NinaVano");
        mapValue2.put("students", students2);
        List<String> teachers2 = new ArrayList<>();
        teachers2.add("LolaIvaniv");
        teachers2.add("NadaPetrov");
        teachers2.add("IgorKoko");
        mapValue2.put("teachers", teachers2);
        mapValue2.put("group", "gr_3");
        mapValue2.put("room", "room 2");
        mapValue2.put("subject", "Maths");
        mapValue2.put("serialNumber", 1);
        mapValue2.put("dayOfweek", DayOfWeek.FRIDAY);
        shedule.add(mapValue2);
        
        Map<String, Object> mapValue3 = new HashMap<>();
        List<String> students3 = new ArrayList<>();
        students3.add("NataSvitlychna");
        students3.add("KataLoza");
        students3.add("NinaVano");
        mapValue3.put("students", students3);
        List<String> teachers3 = new ArrayList<>();
        teachers3.add("LolaIvaniv");
        teachers3.add("NadaPetrov");
        teachers3.add("IgorKoko");
        mapValue3.put("teachers", teachers3);
        mapValue3.put("group", "gr_2");
        mapValue3.put("room", "room 2");
        mapValue3.put("subject", "Maths");
        mapValue3.put("serialNumber", 1);
        mapValue3.put("dayOfweek", DayOfWeek.MONDAY);
        shedule.add(mapValue3);
        
        return shedule;
    }
    
    
    
    private WeekScheduleDto createScheduleSmall(Map<String, Object> mapValue) {

       List<String> students = (List<String>) mapValue.get("students");
       List <StudentDto> studentsDto = new ArrayList<StudentDto>();
       students.forEach(element -> {
       studentsDto.add((StudentDto) new StudentDto().setIdStudent(UUID.randomUUID())
                                                            .setIdPerson(UUID.randomUUID())
                                                            .setFirstName(element.substring(0,4))
                                                            .setLastName(element.substring(4)));
           
       }); 
       
       List<String> teachers = (List<String>) mapValue.get("teachers");
       List<TeacherDto> teachersDto = new ArrayList<TeacherDto>();
       teachers.forEach(element -> {
       teachersDto.add((TeacherDto) new TeacherDto().setIdTeacher(UUID.randomUUID())
                                                           .setIdPerson(UUID.randomUUID())
                                                            .setFirstName(element.substring(0,4))
                                                            .setLastName(element.substring(4)));
           
       }); 
        
        GroupDto group = new GroupDto().setId(UUID.randomUUID()).setName((String) mapValue.get("group")).setStudents(studentsDto);
        
        RoomDto room = new RoomDto().setId(UUID.randomUUID()).setName((String) mapValue.get("room"));
        
        SubjectDto subject = new SubjectDto().setId(UUID.randomUUID()).setName((String) mapValue.get("subject"));
        
        TimeSlotDto timeSlot = new TimeSlotDto().setId(UUID.randomUUID()).setSerialNumber((int) mapValue.get("serialNumber")).setStartTime(LocalTime.of(8,0,0)).setFinishTime(LocalTime.of(9,30,0)); 
        
        ScheduleItemDto scheduleItemDto = new ScheduleItemDto().setId(UUID.randomUUID())
                .setDayOfWeek((DayOfWeek) mapValue.get("dayOfweek"))
                .setGroup(group)
                .setRoom(room)
                .setSubject(subject)
                .setTimeSlot(timeSlot)
                .setTeachers(teachersDto);
        
        
        List <ScheduleItemDto> schedules = new ArrayList<>();
        schedules.add(scheduleItemDto);
        PeriodDto period = new PeriodDto().setId(UUID.randomUUID()).setStartDate(LocalDate.of(2019, 1, 1)).setFinishDate(LocalDate.of(2019, 12, 31));
        WeekScheduleDto weekScheduleDto = new WeekScheduleDto().setSchedules(schedules).setPeriod(period);
        
        return weekScheduleDto;
    }

    
    private WeekScheduleDto createScheduleFull() {

        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        UUID uuid3 = UUID.randomUUID();

        UUID uuidS1 = UUID.randomUUID();
        UUID uuidS2 = UUID.randomUUID();
        UUID uuidS3 = UUID.randomUUID();

        UUID uuidP1 = UUID.randomUUID();
        UUID uuidP2 = UUID.randomUUID();
        UUID uuidP3 = UUID.randomUUID();

        StudentDto student1 = ((StudentDto) new StudentDto().setIdStudent(uuidS1)
                                                            .setIdPerson(uuidP1)
                                                            .setFirstName("Nata1")
                                                            .setLastName("Svitlychna"))
                                                                                       .setCitizenship("Ukraine")
                                                                                       .setGrant(new BigDecimal(111))
                                                                                       .setStartOfStudy(LocalDate.of(
                                                                                               2015, 12, 31))
                                                                                       .setStudyStatus(
                                                                                               StudyStatus.ACCEPTED);

        StudentDto student2 = ((StudentDto) new StudentDto().setIdStudent(uuidS2)
                                                            .setIdPerson(uuidP2)
                                                            .setFirstName("Katja1")
                                                            .setLastName("Loza"))
                                                                                 .setCitizenship("Germany")
                                                                                 .setGrant(new BigDecimal(222))
                                                                                 .setStartOfStudy(
                                                                                         LocalDate.of(2016, 9, 01))
                                                                                 .setStudyStatus(StudyStatus.ACCEPTED);

        StudentDto student3 = ((StudentDto) new StudentDto().setIdStudent(uuidS3)
                                                            .setIdPerson(uuidP3)
                                                            .setFirstName("Nina1")
                                                            .setLastName("Ivanov"))
                                                                                   .setCitizenship("England")
                                                                                   .setGrant(new BigDecimal(333))
                                                                                   .setStartOfStudy(
                                                                                           LocalDate.of(2020, 12, 12))
                                                                                   .setStudyStatus(
                                                                                           StudyStatus.ACCEPTED);
        List <StudentDto> students = new ArrayList<StudentDto>(); 
        students.add(student1);
        students.add(student2);
        students.add(student3);
       
        UUID uuidT1 = UUID.randomUUID();
        UUID uuidT2 = UUID.randomUUID();
        UUID uuidT3 = UUID.randomUUID();

        System.out.println(uuidT1.toString());
        System.out.println(uuidT2.toString());
        System.out.println(uuidT3.toString());
        
        UUID uuidP11 = UUID.randomUUID();
        UUID uuidP22 = UUID.randomUUID();
        UUID uuidP33 = UUID.randomUUID();

        TeacherDto teacher1 = ((TeacherDto) new TeacherDto().setIdTeacher(uuidT1)
                                                            .setIdPerson(uuidP11)
                                                            .setFirstName("Yashwant1")
                                                            .setLastName("Chavan"))
                                                                                   .setDegree(Degree.DOCTOR)
                                                                                   .setDepartment(
                                                                                           Department.INFORMATICS)
                                                                                   .setPermanent(true)
                                                                                   .setSalary(new BigDecimal(1000));

        TeacherDto teacher2 = ((TeacherDto) new TeacherDto().setIdTeacher(uuidT2)
                                                            .setIdPerson(uuidP22)
                                                            .setFirstName("Mahesh1")
                                                            .setLastName("Patil2"))
                                                                                  .setDegree(Degree.DOCTOR)
                                                                                  .setDepartment(Department.INFORMATICS)
                                                                                  .setPermanent(true)
                                                                                  .setSalary(new BigDecimal(2000));

        TeacherDto teacher3 = ((TeacherDto) new TeacherDto().setIdTeacher(uuidT3)
                                                            .setIdPerson(uuidP33)
                                                            .setFirstName("Vishal11")
                                                            .setLastName("Naik"))
                                                                                 .setDegree(Degree.DOCTOR)
                                                                                 .setDepartment(Department.INFORMATICS)
                                                                                 .setPermanent(true)
                                                                                 .setSalary(new BigDecimal(3000));
        
        List <TeacherDto> teachers = new ArrayList<TeacherDto>(); 
        teachers.add(teacher1);
        teachers.add(teacher2);
        teachers.add(teacher3);

        
        GroupDto group1 = new GroupDto().setId(UUID.randomUUID()).setName("gr-1").setSpecialty(Specialty.ECONOMY).setStudents(students);
        
        RoomDto room1 = new RoomDto().setId(UUID.randomUUID()).setName("room1");
        
        SubjectDto subject1 = new SubjectDto().setId(UUID.randomUUID()).setName("Maths");
        
        TimeSlotDto timeSlot1 = new TimeSlotDto().setId(UUID.randomUUID()).setSerialNumber(1).setStartTime(LocalTime.of(8,0,0)).setFinishTime(LocalTime.of(9,30,0)); 
        
        ScheduleItemDto scheduleItemDto = new ScheduleItemDto().setId(uuid1)
                .setDayOfWeek(DayOfWeek.MONDAY)
                .setGroup(group1)
                .setRoom(room1)
                .setSubject(subject1)
                .setTimeSlot(timeSlot1)
                .setTeachers(teachers);
        
        
        List <ScheduleItemDto> schedules = new ArrayList<>();
        schedules.add(scheduleItemDto);
        PeriodDto period = new PeriodDto().setId(UUID.randomUUID()).setStartDate(LocalDate.of(2019, 1, 1)).setFinishDate(LocalDate.of(2019, 12, 31));
        WeekScheduleDto weekScheduleDto = new WeekScheduleDto().setSchedules(schedules).setPeriod(period);
        
        return weekScheduleDto;
    }

    
      public void testTeacher(TeacherService teacherService) {

        UUID uuidT1 = UUID.randomUUID();
        UUID uuidT2 = UUID.randomUUID();
        UUID uuidT3 = UUID.randomUUID();

        UUID uuidP1 = UUID.randomUUID();
        UUID uuidP2 = UUID.randomUUID();
        UUID uuidP3 = UUID.randomUUID();

        TeacherDto teacher1 = ((TeacherDto) new TeacherDto().setIdTeacher(uuidT1)
                                                            .setIdPerson(uuidP1)
                                                            .setFirstName("Yashwant")
                                                            .setLastName("Chavan"))
                                                                                   .setDegree(Degree.DOCTOR)
                                                                                   .setDepartment(
                                                                                           Department.INFORMATICS)
                                                                                   .setPermanent(true)
                                                                                   .setSalary(new BigDecimal(1000));

        TeacherDto teacher2 = ((TeacherDto) new TeacherDto().setIdTeacher(uuidT2)
                                                            .setIdPerson(uuidP2)
                                                            .setFirstName("Mahesh")
                                                            .setLastName("Patil"))
                                                                                  .setDegree(Degree.DOCTOR)
                                                                                  .setDepartment(Department.INFORMATICS)
                                                                                  .setPermanent(true)
                                                                                  .setSalary(new BigDecimal(2000));

        TeacherDto teacher3 = ((TeacherDto) new TeacherDto().setIdTeacher(uuidT3)
                                                            .setIdPerson(uuidP3)
                                                            .setFirstName("Vishal")
                                                            .setLastName("Naik"))
                                                                                 .setDegree(Degree.DOCTOR)
                                                                                 .setDepartment(Department.INFORMATICS)
                                                                                 .setPermanent(true)
                                                                                 .setSalary(new BigDecimal(3000));

        teacherService.addTeacher(teacher1);
        teacherService.addTeacher(teacher2);
        teacherService.addTeacher(teacher3);

        System.out.println("Find All");
        teacherService.findAllTeacher().forEach(System.out::println);

        System.out.println("Delete theacher Id = 3");
        teacherService.deleteTeacher(uuidT3);

        System.out.println("Update person Id = 1");
        teacherService.editTeacher(teacher2.setDegree(Degree.PROFESSOR).setDepartment(Department.ARCHITECTURE), uuidT1);

        System.out.println("Find person Id = 2");
        teacherService.findTeacher(uuidT2).forEach(System.out::println);

        System.out.println("Find All Again");
        teacherService.findAllTeacher().forEach(System.out::println);
    }

    public void testStudent(StudentService studentService) {
        UUID uuidS1 = UUID.randomUUID();
        UUID uuidS2 = UUID.randomUUID();
        UUID uuidS3 = UUID.randomUUID();

        UUID uuidP1 = UUID.randomUUID();
        UUID uuidP2 = UUID.randomUUID();
        UUID uuidP3 = UUID.randomUUID();

        StudentDto student1 = ((StudentDto) new StudentDto().setIdStudent(uuidS1)
                                                            .setIdPerson(uuidP1)
                                                            .setFirstName("Nata")
                                                            .setLastName("Svitlychna"))
                                                                                       .setCitizenship("Ukraine")
                                                                                       .setGrant(new BigDecimal(111))
                                                                                       .setStartOfStudy(LocalDate.of(
                                                                                               2015, 12, 31))
                                                                                       .setStudyStatus(
                                                                                               StudyStatus.ACCEPTED);

        StudentDto student2 = ((StudentDto) new StudentDto().setIdStudent(uuidS2)
                                                            .setIdPerson(uuidP2)
                                                            .setFirstName("Katja")
                                                            .setLastName("Loza"))
                                                                                 .setCitizenship("Germany")
                                                                                 .setGrant(new BigDecimal(222))
                                                                                 .setStartOfStudy(
                                                                                         LocalDate.of(2016, 9, 01))
                                                                                 .setStudyStatus(StudyStatus.ACCEPTED);

        StudentDto student3 = ((StudentDto) new StudentDto().setIdStudent(uuidS3)
                                                            .setIdPerson(uuidP3)
                                                            .setFirstName("Nina")
                                                            .setLastName("Ivanov"))
                                                                                   .setCitizenship("England")
                                                                                   .setGrant(new BigDecimal(333))
                                                                                   .setStartOfStudy(
                                                                                           LocalDate.of(2020, 12, 12))
                                                                                   .setStudyStatus(
                                                                                           StudyStatus.ACCEPTED);

        studentService.addStudent(student1);
        studentService.addStudent(student2);
        studentService.addStudent(student3);

        System.out.println("Find All");
        studentService.findAllStudent().forEach(System.out::println);

        System.out.println("Delete person Id = 3");
        studentService.deleteStudent(uuidS3);

        System.out.println("Update person Id = 1");
        studentService.editStudent(student1.setCitizenship("Russia")
                                           .setStudyStatus(StudyStatus.FINISHED)
                                           .setGrant(new BigDecimal(10))
                                           .setStartOfStudy(LocalDate.of(1999, 02, 02)),
                uuidS1);

        System.out.println("Find person Id = 2");
        studentService.findStudent(uuidS2).forEach(System.out::println);

        System.out.println("Find All Again");
        studentService.findAllStudent().forEach(System.out::println);
    }
    
}
