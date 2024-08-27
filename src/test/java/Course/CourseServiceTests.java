package Course;

import Controller.Course;
import task3.Exceptions.CourseExistsException;
import task3.Exceptions.CourseNotFoundException;
import task3.Exceptions.IllegalArgumentException;
import task3.Mappers.CourseDTO;
import task3.Mappers.CourseMapper;
import task3.Repositories.CourseRepository;
import task3.Services.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = task3.UdemyClone.class)
public class CourseServiceTests {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private CourseMapper courseMapper;

    @InjectMocks
    private CourseService courseService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddCourse_Success() {
        CourseDTO courseDTO = new CourseDTO("Test Course", "Description", 5);
        Course course = new Course("Test Course", "Description", 5);
        Mockito.when(courseRepository.findByName(course.getName())).thenReturn(Optional.empty());
        Mockito.when(courseMapper.courseDTOToCourse(courseDTO)).thenReturn(course);

        assertDoesNotThrow(() -> courseService.addCourse(courseDTO));
        Course save = courseMapper.courseDTOToCourse(courseDTO);
        Mockito.verify(courseRepository, Mockito.times(1)).save(save);
    }

    @Test
    public void testAddCourse_CourseExists() {
        Course course = new Course("Test Course", "Description", 5);
        CourseDTO  courseDTO = new CourseDTO("Test Course", "Description", 5);
        Mockito.when(courseRepository.findByName(course.getName())).thenReturn(Optional.of(course));
        Mockito.when(courseMapper.courseDTOToCourse(courseDTO)).thenReturn(course);

        Exception exception = assertThrows(CourseExistsException.class, () -> courseService.addCourse(courseDTO));
        assertEquals("Course with the same name already exists.", exception.getMessage());
    }

    @Test
    public void testAddCourse_MissingName() {
        CourseDTO courseDTO = new CourseDTO("", "Description", 5);
        Course course = new Course("", "Description", 5);
        Mockito.when(courseRepository.findByName(course.getName())).thenReturn(Optional.of(course));
        Mockito.when(courseMapper.courseDTOToCourse(courseDTO)).thenReturn(course);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> courseService.addCourse(courseDTO));
        assertEquals("Course name is required.", exception.getMessage());
    }
    @Test
    public void testAddCourse_NullName() {
        CourseDTO courseDTO = new CourseDTO(null, "Description", 5);
        Course course = new Course(null, "Description", 5);
        Mockito.when(courseRepository.findByName(course.getName())).thenReturn(Optional.of(course));
        Mockito.when(courseMapper.courseDTOToCourse(courseDTO)).thenReturn(course);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> courseService.addCourse(courseDTO));
        assertEquals("Course name is required.", exception.getMessage());
    }

    @Test
    public void testAddCourse_MissingCredit() {
        CourseDTO courseDTO = new CourseDTO("Test Course", "Description", 0);
        Course course = new Course("Test Course", "Description", 0);
        Mockito.when(courseRepository.findByName(course.getName())).thenReturn(Optional.of(course));
        Mockito.when(courseMapper.courseDTOToCourse(courseDTO)).thenReturn(course);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> courseService.addCourse(courseDTO));
        assertEquals("Course credit must be a positive number.", exception.getMessage());

    }

    @Test
    public void testAddCourse_MissingDescription() {
        CourseDTO courseDTO = new CourseDTO("Test Course", "", 5);
        Course course = new Course("Test Course", "", 5);
        Mockito.when(courseRepository.findByName(course.getName())).thenReturn(Optional.of(course));
        Mockito.when(courseMapper.courseDTOToCourse(courseDTO)).thenReturn(course);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> courseService.addCourse(courseDTO));

        assertEquals("Course description is required.", exception.getMessage());
    }
    @Test
    public void testAddCourse_NullDescription() {
        CourseDTO courseDTO = new CourseDTO("Test Course", null, 5);
        Course course = new Course("Test Course", null, 5);
        Mockito.when(courseRepository.findByName(course.getName())).thenReturn(Optional.of(course));
        Mockito.when(courseMapper.courseDTOToCourse(courseDTO)).thenReturn(course);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> courseService.addCourse(courseDTO));

        assertEquals("Course description is required.", exception.getMessage());
    }

    @Test
    public void testUpdateCourse_Success() {
        Course course = new Course("Test Course", "Description", 5);
        Course updatedCourse = new Course("Updated Course", "Updated Description", 6);
        CourseDTO courseDTO = new CourseDTO("Updated Course", "Updated Description", 6);
        Mockito.when(courseMapper.courseDTOToCourse(courseDTO)).thenReturn(updatedCourse);
        Mockito.when(courseRepository.findByName("Test Course")).thenReturn(Optional.of(course));
        assertDoesNotThrow(() -> courseService.updateCourse("Test Course", courseDTO));
        Mockito.verify(courseRepository, Mockito.times(1)).save(course);
    }

    @Test
    public void testUpdateCourse_CourseNotFound() {
        CourseDTO updatedCourse = new CourseDTO("Updated Course", "Updated Description", 6);

        Mockito.when(courseRepository.findByName("Non-existent Course")).thenReturn(Optional.empty());

        assertThrows(CourseNotFoundException.class, () -> courseService.updateCourse("Non-existent Course", updatedCourse));
    }
    @Test
    public void testUpdateCourse_InvalidCredit() {
        Course course = new Course("Test Course", "Description", 5);
        Course updatedCourse = new Course("Updated Course", "Updated Description", 0);
        CourseDTO courseDTO = new CourseDTO("Updated Course", "Updated Description", 0);
        Mockito.when(courseMapper.courseDTOToCourse(courseDTO)).thenReturn(updatedCourse);
        Mockito.when(courseRepository.findByName("Test Course")).thenReturn(Optional.of(course));
        Exception exception = assertThrows(IllegalArgumentException.class, () -> courseService.updateCourse("Test Course", courseDTO));
        assertEquals("Course credit must be a positive number.", exception.getMessage());
    }

    @Test
    public void testViewCourse_Success() {
        Course course = new Course("Test Course", "Description", 5);
        CourseDTO courseDTO = new CourseDTO("Test Course", "Description",5);

        Mockito.when(courseRepository.findByName("Test Course")).thenReturn(Optional.of(course));
        Mockito.when(courseMapper.courseToCourseDTO(course)).thenReturn(courseDTO);

        CourseDTO result = courseService.viewCourse("Test Course");
        assertEquals(result.getName(),course.getName());
        Mockito.verify(courseRepository, Mockito.times(1)).findByName("Test Course");
    }

    @Test
    public void testViewCourse_CourseNotFound() {
        Mockito.when(courseRepository.findByName("Non-existent Course")).thenReturn(Optional.empty());

        assertThrows(CourseNotFoundException.class, () -> courseService.viewCourse("Non-existent Course"));
    }

    @Test
    public void testDeleteCourse_Success() {
        Course course = new Course("Test Course", "Description", 5);

        Mockito.when(courseRepository.findByName("Test Course")).thenReturn(Optional.of(course));

        assertDoesNotThrow(() -> courseService.deleteCourse("Test Course"));
        Mockito.verify(courseRepository, Mockito.times(1)).delete(course);


    }



    @Test
    public void testDeleteCourse_NotFound() {
        Mockito.when(courseRepository.findByName("Non-existent Course")).thenReturn(Optional.empty());

        assertThrows(CourseNotFoundException.class, () -> courseService.deleteCourse("Non-existent Course"));

    }

    @Test
    public void testViewAllCourses_Success() {
        Course course1 = new Course("Course 1", "Description 1", 5);
        Course course2 = new Course("Course 2", "Description 2", 3);
        Page<Course> coursesPage = new PageImpl<>(Arrays.asList(course1, course2));

        PageRequest pageable = PageRequest.of(0, 10);
        Mockito.when(courseRepository.findAll(pageable)).thenReturn(coursesPage);

        CourseDTO courseDTO1 = new CourseDTO("Course 1", "Description 1",5);
        CourseDTO courseDTO2 = new CourseDTO("Course 2", "Description 2",3);

        Mockito.when(courseMapper.courseToCourseDTO(course1)).thenReturn(courseDTO1);
        Mockito.when(courseMapper.courseToCourseDTO(course2)).thenReturn(courseDTO2);

        List<CourseDTO> result = courseService.viewAllCourses(pageable);
        assertEquals(course1.getName(), result.get(0).getName());
        assertEquals(course2.getName(), result.get(1).getName());


        Mockito.verify(courseRepository, Mockito.times(1)).findAll(pageable);
    }
}
