package task3;
import Controller.Course;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        CourseService courseService = context.getBean(CourseService.class);

        Course course = courseService.viewCourse(3);
        System.out.println("Course Details: " + course.getName() + " - " + course.getDescription());
    }
}