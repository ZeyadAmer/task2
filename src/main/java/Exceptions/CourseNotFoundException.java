package Exceptions;

public class CourseNotFoundException extends RuntimeException{
    public CourseNotFoundException(){
        super("Course Not Found");
    }
}
