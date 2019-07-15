package cc.stan.example.graphql.resolver;

import cc.stan.example.graphql.model.Author;
import cc.stan.example.graphql.model.Book;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class AuthorQuery implements GraphQLQueryResolver {

    public List<Author> queryAuthor() {
        return Arrays.asList(
                new Author(1L, "hello"),
                new Author(2L, "world")
        );
    }

    public List<Book> queryBook() {
        return Arrays.asList(
                new Book(1L, "book1", Arrays.asList(new Author(1L, "hello"))),
                new Book(2L, "book1", Arrays.asList(new Author(2L, "world")))
        );
    }

}
