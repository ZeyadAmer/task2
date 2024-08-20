package Controller;

import Mappers.CourseDTO;
import Services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;


import java.util.List;

@RestController
@RequestMapping("/courses")
@Service
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/{id}")
    public CourseDTO getCourse(@PathVariable int id) {
        return courseService.viewCourse(id);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addCourse(@RequestBody Course newCourse) {
        courseService.addCourse(newCourse);
        return ResponseEntity.ok("Course added successfully!");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCourse(@PathVariable int id, @RequestBody Course updatedCourse) {
        courseService.updateCourse(id,updatedCourse);
        return ResponseEntity.ok("Course updated successfully!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable int id) {
        courseService.deleteCourse(id);
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


}
