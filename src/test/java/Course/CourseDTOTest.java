package Course;

import org.springframework.beans.factory.annotation.Autowired;
import task3.Mappers.CourseDTO;
import task3.Mappers.CourseMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Controller.Course;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;



@SpringBootTest(classes = task3.UdemyClone.class)
public class CourseDTOTest {
    @Autowired
    private CourseMapper courseMapper;



    CourseDTO courseDTO;
    Course course;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        courseDTO = new CourseDTO();
        course = new Course();
    }

    @Test
    public void testCourseDTO_SetDescription() {courseDTO.setDescription("Test");
       assertEquals("Test",courseDTO.getDescription(),"Description should be 'Test'");
    }
    @Test
    public void testCourseToCourseDTO_NullCourse() {
        courseDTO = courseMapper.courseToCourseDTO(null);
        assertNull(courseDTO,"CourseDTO should be null");
    }
    @Test
    public void testCourseToCourseDTO() {
        Course course = new Course("Test" , "Description",5);
        courseDTO = courseMapper.courseToCourseDTO(course);
        assertEquals("Test",courseDTO.getName(),"name should be 'Test'");
        assertEquals("Description",courseDTO.getDescription(),"Description should be 'Description'");
    }
    @Test
    public void testCourseDTOToCourse_NullCourse() {
        course = courseMapper.courseDTOToCourse(null);
        assertNull(course,"CourseDTO should be null");
    }
    @Test
    public void testCourseDTOToCourse() {
        CourseDTO courseDTO = new CourseDTO("Test" , "Description",5);

        course = courseMapper.courseDTOToCourse(courseDTO);
        assertEquals("Test",course.getName(),"name should be 'Test'");
        assertEquals("Description",course.getDescription(),"Description should be 'Description'");
    }

}
