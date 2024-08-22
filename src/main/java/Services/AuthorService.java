package Services;

import Controller.Author;
import Exceptions.*;
import Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
