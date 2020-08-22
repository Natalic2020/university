package ua.com.foxminded;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import ua.com.foxminded.config.ApplicationConfig;
import ua.com.foxminded.dao.TablesInitializer;
import ua.com.foxminded.dao.entity.TimeSlot;
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
import ua.com.foxminded.service.ScheduleService;
import ua.com.foxminded.service.StudentService;
import ua.com.foxminded.service.TeacherService;

public class Application {

//    @Autowired
//    StudentTransformer studentTransformer;

    public static void main(String args[]) {

        AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

//      DatabaseInitializer dbInitializer = context.getBean("databaseInitializer", DatabaseInitializer.class);
//      dbInitializer.createDB();  

        TablesInitializer tableInitializer = context.getBean("tablesInitializer", TablesInitializer.class);
        tableInitializer.createTables();

//        StudentService studentService = (StudentService) context.getBean("studentService");
//        TestStudent(studentService);
//
//        TeacherService teacherService = (TeacherService) context.getBean("teacherService");
//        TestTeacher(teacherService);
//
        ScheduleService scheduleService = (ScheduleService) context.getBean("scheduleService");
        TestSchedule(scheduleService);
        List<ScheduleItemDto> schedule = scheduleService.findScheduleTeacher(UUID.randomUUID(), "", "");
        
        LocalDate.of(2016, 9, 01).getDayOfWeek();   
        
        
//        scheduleService.fillTable();;
        

        context.close();
    }

    private static void TestSchedule(ScheduleService scheduleService) {

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
        
        scheduleService.addSchedule(weekScheduleDto);
    }

    private static void TestTeacher(TeacherService teacherService) {

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

    private static void TestStudent(StudentService studentService) {
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
