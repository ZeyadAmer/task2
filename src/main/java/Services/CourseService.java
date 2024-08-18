package Services;

import Controller.Course;
import Mappers.CourseDTO;
import Mappers.CourseMapper;
import Repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseMapper courseMapper;


    public void addCourse(Course course) {
        courseRepository.save(course);
    }
    public void updateCourse(int id, Course updatedCourse) {
        Optional<Course> existingCourse = courseRepository.findById(id);
        if (existingCourse.isPresent()) {
            Course course = existingCourse.get();
            course.setName(updatedCourse.getName());
            course.setDescription(updatedCourse.getDescription());
            course.setCredit(updatedCourse.getCredit());
            courseRepository.save(course);
        }
    }

    public Course viewCourse(int id) {
        return courseRepository.findById(id).orElse(null);
    }

    public void deleteCourse(int id) {
        courseRepository.deleteById(id);
    }

    public List<CourseDTO> viewAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(courseMapper::toCourseDTO)
                .collect(Collectors.toList());
    }

//    public List<Course> getRecommendedCourses() {
//
//    }

}
