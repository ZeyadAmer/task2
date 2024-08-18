package Mappers;

import Controller.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorDTO mapAuthorDTO(Author author);
    Author mapAuthor(AuthorDTO authorDTO);
}
