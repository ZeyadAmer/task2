package task2;
import Controller.Course;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        CourseService courseService = context.getBean(CourseService.class);
        courseService.getRecommendedCourses().forEach(course -> System.out.println(course.getName()));

        courseService.addCourse("Mathematics", "Basic Math Course", 3);
        System.out.println("Course added!");



        courseService.viewAllCourses().forEach(course ->
                System.out.println(course.getId() + ": " + course.getName() + " - " + course.getDescription() + " (" + course.getCredit() + " credits)")
        );



        courseService.updateCourse(2, "Mathematics", "Updated Math Course", 4);



        Course updatedCourse = courseService.viewCourse(2);
        System.out.println(updatedCourse.getId() + ": " + updatedCourse.getName() + " - " + updatedCourse.getDescription() + " (" + updatedCourse.getCredit() + " credits)");

        // Test deleteCourse

        courseService.deleteCourse(1);
        System.out.println("Course deleted!");



        courseService.viewAllCourses().forEach(course ->
                System.out.println(course.getId() + ": " + course.getName() + " - " + course.getDescription() + " (" + course.getCredit() + " credits)")
        );
    }
}