package Services;

import Controller.Author;
import Controller.Course;
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

    public void addAuthor(Author author) {
        authorRepository.save(author);
    }
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
    public Author getAuthorById(int id) {
        return authorRepository.findById(id).orElse(null);
    }
    public void deleteAuthorById(int id) {
        authorRepository.deleteById(id);
    }
    public void updateAuthor(int id,Author updatedAuthor) {
        Optional<Author> existingCourse = authorRepository.findById(id);
        if (existingCourse.isPresent()) {
            Author author = existingCourse.get();
            author.setName(updatedAuthor.getName());
            author.setEmail(updatedAuthor.getEmail());
            author.setBirthdate(updatedAuthor.getBirthdate());
            authorRepository.save(author);
        }
    }
    public Author getAuthorByEmail(String email) {
        return authorRepository.findByEmail(email);

    }
}
