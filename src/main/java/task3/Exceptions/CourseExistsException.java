package task3.Exceptions;

public class CourseExistsException extends RuntimeException{
    public CourseExistsException(){
        super("Course with the same name already exists.");
    }
}
