package Author;

import Controller.Author;
import task3.Controller.AuthorController;
import task3.Exceptions.AuthorNotFoundException;
import task3.Services.AuthorService;
import task3.Exceptions.InvalidEmailFormatException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(AuthorController.class)
@ContextConfiguration(classes = AuthorController.class)
@ComponentScan(basePackages = "task3/Exceptions")
public class AuthorControllerTests {
    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private AuthorService authorService;

    @Test
    public void testFindByEmail_AuthorExists() throws Exception {
        Author author = new Author();
        author.setEmail("test@example.com");

        when(authorService.getAuthorByEmail("test@example.com")).thenReturn(author);

        mockMvc.perform(get("/authors/email")
                        .param("email", "test@example.com")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }

    @Test
    public void testFindByEmail_AuthorNotFound() throws Exception {

        when(authorService.getAuthorByEmail("notfound@example.com")).thenThrow(new AuthorNotFoundException());

        mockMvc.perform(get("/authors/email")
                        .param("email", "notfound@example.com")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testFindByEmail_InvalidEmailFormat() throws Exception {
        when(authorService.getAuthorByEmail(anyString())).thenThrow(InvalidEmailFormatException.class);
        mockMvc.perform(get("/authors/email")
                        .param("email", "invalid-email")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}