package Controller;

import Mappers.CourseDTO;
import Services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import Services.*;


import java.util.List;

@RestController
@RequestMapping("/courses")
@Service
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/{id}")
    public Course getCourse(@PathVariable int id) {
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
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> courses = courseService.viewAllCourses();
        return ResponseEntity.ok(courses);
    }
//    @GetMapping("recommended")
//    public ResponseEntity<List<Course>> getRecommendedCourses() {
//        List<Course> courses = courseService.getRecommendedCourses();
//        return ResponseEntity.ok(courses);
//    }
}
