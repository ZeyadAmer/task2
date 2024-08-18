package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    public void addCourse(String name, String description, int credit) {
        courseRepository.addCourse(name, description,credit);
    }
    public void updateCourse(int id, String name, String description, int credit) {
        courseRepository.updateCourse(id,name,description,credit);
    }

    public Course viewCourse(int id) {
        return courseRepository.viewCourse(id);
    }

    public void deleteCourse(int id) {
        courseRepository.deleteCourse(id);
    }

    public List<Course> viewAllCourses() {
        return courseRepository.viewAllCourses();
    }

    public List<Course> getRecommendedCourses() {
        return courseRepository.getRecommendedCourses();
    }

}
