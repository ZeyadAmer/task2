package task3.Exceptions;

public class InvalidEmailFormatException extends RuntimeException {
    public InvalidEmailFormatException() {
        super("Invalid email format");
    }
}