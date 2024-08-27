package task3.Services;

import Controller.Course;
import task3.Exceptions.CourseExistsException;
import task3.Exceptions.CourseNotFoundException;
import task3.Exceptions.IllegalArgumentException;
import task3.Mappers.CourseDTO;
import task3.Mappers.CourseMapper;
import task3.Repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    private Course discover;

    @Autowired
    public CourseService(Course discover) {
        this.discover = discover;
    }



    public void addCourse(CourseDTO courseDTO) {
        Course course = courseMapper.courseDTOToCourse(courseDTO);
        Optional<Course> existingCourse = courseRepository.findByName(course.getName());
        if (course.getName() == null || course.getName().isEmpty()) {
            throw new IllegalArgumentException("Course name is required.");
        }
        if (course.getDescription() == null || course.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Course description is required.");
        }
        if (course.getCredit() <= 0) {
            throw new IllegalArgumentException("Course credit must be a positive number.");
        }
        if (existingCourse.isPresent()) {
            throw new CourseExistsException();
        }

        courseRepository.save(course);
    }
    public void updateCourse(String name, CourseDTO updatedCourseDTO) {
        Optional<Course> existingCourse = courseRepository.findByName(name);
        Course updatedCourse = courseMapper.courseDTOToCourse(updatedCourseDTO);
        if (existingCourse.isPresent()) {
            Course course = existingCourse.get();
            if (updatedCourse.getName() != null && !updatedCourse.getName().isEmpty()) {
                course.setName(updatedCourse.getName());
            }
            if (updatedCourse.getDescription() != null && !updatedCourse.getDescription().isEmpty()) {
                course.setDescription(updatedCourse.getDescription());
            }

            if (updatedCourse.getCredit() <= 0) {
                throw new IllegalArgumentException("Course credit must be a positive number.");
            }else course.setCredit(updatedCourse.getCredit());
            courseRepository.save(course);
        } else {
            throw new CourseNotFoundException();
        }
    }

    public CourseDTO viewCourse(String name) {
        Course course = courseRepository.findByName(name).orElseThrow(CourseNotFoundException::new);
        return courseMapper.courseToCourseDTO(course);
    }

    public void deleteCourse(String name) {
        Optional<Course> existingCourse = courseRepository.findByName(name);
        if (existingCourse.isPresent()) {
            courseRepository.delete(existingCourse.get());
        } else {
            throw new CourseNotFoundException();
        }

    }

    public List<CourseDTO> viewAllCourses(Pageable pageable) {
        Page<Course> courses = courseRepository.findAll(pageable);
        return courses.stream().map(courseMapper::courseToCourseDTO).collect(Collectors.toList());
    }

    public CourseDTO getCourseWithLowestCredit() {
        return courseMapper.courseToCourseDTO(discover);
    }


}
