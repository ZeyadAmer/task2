package Controller;

import java.sql.Date;

public class Author {
    int id;
    String name;
    String email;
    Date birthdate;
    public Author(int id, String name, String email, Date birthdate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthdate = birthdate;
    }
}
