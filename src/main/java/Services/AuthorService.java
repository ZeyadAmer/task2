package Services;

import Controller.Author;
import Controller.Course;
import Exceptions.*;
import Mappers.AuthorMapper;
import Repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private AuthorMapper authorMapper;

    public Author getAuthorByEmail(String email) {
        if (!email.contains("@")) {
            throw new InvalidEmailFormatException();
        }
        return authorRepository.findByEmail(email).orElseThrow(AuthorNotFoundException::new);

    }
}
