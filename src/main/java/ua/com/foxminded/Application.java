package ua.com.foxminded;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.com.foxminded.dao.DatabaseInitializer;
import ua.com.foxminded.dao.TablesInitializer;

public class Application {

//    @Autowired
//    private static Student student;
    
    public static void main(String[] args) {
        
//        DatabaseInitializer dbInitializer = new DatabaseInitializer();
//        dbInitializer.createDB();
        
        ApplicationContext context =  new ClassPathXmlApplicationContext("Spring-Module.xml");
        DatabaseInitializer dbInitializer = context.getBean("databaseInitializer", DatabaseInitializer.class);
        dbInitializer.createDB();
        
        
        TablesInitializer dBInitializer   = context.getBean("tablesInitializer", TablesInitializer.class);
        
        dBInitializer.createTables();
        
//            Student student = new Student(UUID.randomUUID(), "mkyong", "kuku");
//            StudentDao studentDao= (StudentDao) context.getBean("studentDao");
//            
//            System.out.println(student.toString());
//            studentDao.insert(student);
//            
//            List<Student> students = studentDao.receiveAllStudents();
//            students.stream().forEach(student1 -> System.out.println(student1.toString()));
    }
    
    
//    public static void main(String[] args) {
//
//        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Application.class);
//        Application app = ctx.getBean(Application.class);
//
//        app.run();
//
//        ctx.close();
//    }
//
//    @Autowired
//    private IStudentService studentService;
//
//    private void run() {
//
//        System.out.println("Fetching a car with Id 3");
//        Long id = 3L;
//        Student car = (Student) studentService.findById(id);
//        System.out.println(car);
//
//        System.out.println("Fetching all cars");
//        List<Student> students = studentService.all();
//        students.forEach(System.out::println);
//    }

}
