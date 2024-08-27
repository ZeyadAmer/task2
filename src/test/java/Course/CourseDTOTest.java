package Course;

import task3.Mappers.CourseDTO;
import task3.Mappers.CourseMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import Controller.Course;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;


@SpringBootTest(classes = task3.UdemyClone.class)
public class CourseDTOTest {
    @Mock
    private CourseMapper courseMapper;

    @InjectMocks
    private CourseDTO courseDTO;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCourseDTO_SetDescription() {
        courseDTO.setDescription("Test");
       assertEquals("Test",courseDTO.getDescription(),"Description should be 'Test'");
    }
    @Test
    public void testCourseDTO_NullCourse() {
        Course course = null;
        when(courseMapper.courseToCourseDTO(course)).thenReturn(null);
        courseDTO = courseMapper.courseToCourseDTO(course);
        assertNull(courseDTO,"CourseDTO should be null");
    }
    @Test
    public void testCourseDTO() {
        Course course = new Course("Test" , "Description",5);
        CourseDTO mockedCourseDTO = new CourseDTO();
        mockedCourseDTO.setName("Test");
        mockedCourseDTO.setDescription("Description");

        when(courseMapper.courseToCourseDTO(course)).thenReturn(mockedCourseDTO);

        courseDTO = courseMapper.courseToCourseDTO(course);
        assertEquals("Test",courseDTO.getName(),"name should be 'Test'");
        assertEquals("Description",courseDTO.getDescription(),"Description should be 'Description'");
    }


}
