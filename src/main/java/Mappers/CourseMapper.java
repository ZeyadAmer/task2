package Mappers;

import Controller.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    CourseDTO toCourseDTO(Course course);
}
