package Exceptions;

public class IllegalArgumentException extends RuntimeException{
    public IllegalArgumentException(String errorMessage){
        super(errorMessage);
    }
}
