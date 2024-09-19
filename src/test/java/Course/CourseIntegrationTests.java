package Course;
import Controller.Course;
import task3.Controller.CourseController;
import task3.Exceptions.CourseExistsException;
import task3.Exceptions.CourseNotFoundException;
import task3.Mappers.CourseDTO;
import task3.Repositories.CourseRepository;
import task3.Services.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = task3.UdemyClone.class)
public class CourseIntegrationTests {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseController courseController;

    @Test
    public void testAddCourse_Success() {
        CourseDTO course = new CourseDTO("TestCourse10", "Description", 5);
        courseController.addCourse(course);
        CourseDTO savedCourse = courseController.getCourse(course.getName());
        assertNotNull(savedCourse, "The course should have been saved in the repository.");
        assertEquals(course.getName(), savedCourse.getName());
        assertEquals(course.getDescription(), savedCourse.getDescription());
        courseController.deleteCourse(course.getName());
        //remove the course from the database to be able to run test data more than once
    }
    @Test
    public void testAddCourse_DuplicateCourseName() {
        CourseDTO course1 = new CourseDTO("Duplicate Course", "Description", 3);
        courseController.addCourse(course1);
        CourseDTO duplicateCourse = new CourseDTO("Duplicate Course", "Another description", 4);
        assertThrows(CourseExistsException.class, () -> {
            courseController.addCourse(duplicateCourse);
        });
        courseController.deleteCourse(course1.getName());
        //remove the course from the database to be able to run test data more than once
    }
    @Test
    public void testUpdateCourse_Success() {
        CourseDTO course = new CourseDTO("TestCourse10", "Description", 5);
        CourseDTO updatedCourse = new CourseDTO("TestCourse10", "Another description", 4);
        courseController.addCourse(course);
        courseController.updateCourse("TestCourse10", updatedCourse);
        CourseDTO savedCourse = courseController.getCourse(course.getName());
        assertNotNull(savedCourse, "The course should have been saved in the repository.");
        assertEquals(updatedCourse.getName(), savedCourse.getName());
        assertEquals(updatedCourse.getDescription(), savedCourse.getDescription());
        courseController.deleteCourse(course.getName());
        //remove the course from the database to be able to run test data more than once
    }
    @Test
    public void testUpdateCourse_CourseDoesNotExist(){
        CourseDTO updatedCourse = new CourseDTO("TestCourse106", "Another description", 4);
        assertThrows(CourseNotFoundException.class, () ->
                courseController.updateCourse("TestCourse10", updatedCourse));
        //remove the course from the database to be able to run test data more than once

    }
    @Test
    public void testDeleteCourse_Success() {
        CourseDTO course = new CourseDTO("TestCourse10", "Description", 5);
        courseController.addCourse(course);
        courseController.deleteCourse("TestCourse10");
        assertThrows(CourseNotFoundException.class, () -> courseController.getCourse(course.getName()));
    }
    @Test
    public void testGetCourse_NotExist() {
        assertThrows(CourseNotFoundException.class, () -> courseController.getCourse("TestCourse10"));
    }
}
