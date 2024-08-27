package task3.Services;

import Controller.Author;
import task3.Exceptions.InvalidEmailFormatException;
import task3.Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task3.Exceptions.AuthorNotFoundException;


@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;


    public Author getAuthorByEmail(String email) {
        if (!email.contains("@")) {
            throw new InvalidEmailFormatException();
        }
        return authorRepository.findByEmail(email).orElseThrow(AuthorNotFoundException::new);

    }
}
