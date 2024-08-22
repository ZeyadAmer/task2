package Course;

import Controller.Course;
import Controller.CourseController;
import Exceptions.*;
import Exceptions.IllegalArgumentException;
import Mappers.CourseDTO;
import Services.CourseService;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CourseController.class)
@ContextConfiguration(classes = CourseController.class)
@ComponentScan(basePackages = "Exceptions")
public class CourseControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    @Test
    public void testViewCourse_Success() throws Exception {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setName("Test Course");
        when(courseService.viewCourse("Test Course")).thenReturn(courseDTO);

        mockMvc.perform(get("/courses/viewCourse")
                        .param("name", "Test Course")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Course"));
    }

    @Test
    public void testViewCourse_NotFound() throws Exception {
        when(courseService.viewCourse("Non-existent Course")).thenThrow(new CourseNotFoundException());

        mockMvc.perform(get("/courses/viewCourse")
                        .param("name", "Non-existent Course"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testAddCourse_Success() throws Exception {
        Course course = new Course("Test Course", "Description", 5);

        mockMvc.perform(post("/courses/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Test Course\", \"description\":\"Description\", \"credit\":5}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Course added successfully!"));
    }

    @Test
    public void testAddCourse_CourseExists() throws Exception {
        Course course = new Course("Test Course", "Description", 5);
        Mockito.doThrow(new CourseExistsException())
                .when(courseService).addCourse(any(Course.class));

        mockMvc.perform(post("/courses/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Test Course\", \"description\":\"Description\", \"credit\":5}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Course with the same name already exists."));
    }
    @Test
    public void testAddCourse_MissingName() throws Exception {
        Course course = new Course(null, "Description", 5);
        Mockito.doThrow(new IllegalArgumentException("Course name is required."))
                .when(courseService).addCourse(any(Course.class));

        mockMvc.perform(post("/courses/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Test Course\", \"description\":\"Description\", \"credit\":5}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Course name is required."));
    }

    @Test
    public void testUpdateCourse_Success() throws Exception {
        mockMvc.perform(put("/courses/update")
                        .param("name", "Test Course")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Updated Course\", \"description\":\"Updated Description\", \"credit\":6}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Course updated successfully!"));
    }

    @Test
    public void testUpdateCourse_NotFound() throws Exception {
        Mockito.doThrow(new CourseNotFoundException())
                .when(courseService).updateCourse(any(String.class), any(Course.class));

        mockMvc.perform(put("/courses/update")
                        .param("name", "Non-existent Course")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Updated Course\", \"description\":\"Updated Description\", \"credit\":6}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeleteCourse_Success() throws Exception {
        mockMvc.perform(delete("/courses/delete")
                        .param("name", "Test Course"))
                .andExpect(status().isOk())
                .andExpect(content().string("Course deleted successfully!"));
    }

    @Test
    public void testDeleteCourse_NotFound() throws Exception {
        Mockito.doThrow(new CourseNotFoundException())
                .when(courseService).deleteCourse(any(String.class));

        mockMvc.perform(delete("/courses/delete")
                        .param("name", "Non-existent Course"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllCourses_Success() throws Exception {
        CourseDTO courseDTO1 = new CourseDTO(); // Populate DTO fields as required
        CourseDTO courseDTO2 = new CourseDTO(); // Populate DTO fields as required

        List<CourseDTO> courses = Arrays.asList(courseDTO1, courseDTO2);

        when(courseService.viewAllCourses(any())).thenReturn(courses);

        mockMvc.perform(get("/courses/all")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}
