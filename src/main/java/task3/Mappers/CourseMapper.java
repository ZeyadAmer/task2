package task3.Mappers;

import Controller.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseDTO courseToCourseDTO(Course course);
    Course courseDTOToCourse(CourseDTO courseDTO);
}
