package Exceptions;

public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException(){
        super("Author not found");
    }

}
