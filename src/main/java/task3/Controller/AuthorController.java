package task3.Controller;


import Controller.Author;
import task3.Services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/authors")
@Service
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping("/email")
    public Author findByEmail(@RequestParam String email) {
        return authorService.getAuthorByEmail(email);
    }
}
