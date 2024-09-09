package Author;

import Controller.Author;
import org.springframework.test.context.ContextConfiguration;
import task3.Controller.CourseController;
import task3.Repositories.AuthorRepository;
import task3.Services.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import task3.Exceptions.AuthorNotFoundException;
import task3.Exceptions.InvalidEmailFormatException;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = task3.UdemyClone.class)

public class AuthorServiceTests {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAuthorByEmail_AuthorExists() {
        Author author = new Author();
        author.setEmail("test@example.com");

        when(authorRepository.findByEmail("test@example.com"))
                .thenReturn(Optional.of(author));

        Author result = authorService.getAuthorByEmail("test@example.com");

        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
    }

    @Test
    public void testGetAuthorByEmail_AuthorNotFound() {
        when(authorRepository.findByEmail("notfound@example.com"))
                .thenReturn(Optional.empty());

        assertThrows(AuthorNotFoundException.class, () -> {
            authorService.getAuthorByEmail("notfound@example.com");
        });
    }

    @Test
    public void testGetAuthorByEmail_InvalidEmailFormat() {
        assertThrows(InvalidEmailFormatException.class, () -> {
            authorService.getAuthorByEmail("invalidemail");
        });
    }
}
