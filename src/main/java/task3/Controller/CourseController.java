package task3.Controller;

import task3.Mappers.CourseDTO;
import task3.Services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/courses")
@Service
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/viewCourse")
    public CourseDTO getCourse(@RequestParam String name) {
        return courseService.viewCourse(name);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addCourse(@Valid @RequestBody CourseDTO newCourse) {
        courseService.addCourse(newCourse);
        return ResponseEntity.ok("Course added successfully!");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCourse(@RequestParam String name,@Valid @RequestBody CourseDTO updatedCourse) {
        courseService.updateCourse(name,updatedCourse);
        return ResponseEntity.ok("Course updated successfully!");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCourse(@RequestParam String name) {
        courseService.deleteCourse(name);
        return ResponseEntity.ok("Course deleted successfully!");
    }

    @GetMapping("/all")
    public ResponseEntity<List<CourseDTO>> getCourses(@RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<CourseDTO>
                courses = courseService.viewAllCourses(pageable);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/leastCredit")
    public CourseDTO getLeastCredit() {
        return courseService.getCourseWithLowestCredit();
    }

}
