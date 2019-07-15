package cc.stan.example.graphql.resolver;

import cc.stan.example.graphql.model.Author;
import cc.stan.example.graphql.model.Book;
import com.coxautodev.graphql.tools.GraphQLResolver;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class BookResolver implements GraphQLResolver<Book> {

    public List<Author> authors(Book book) {
        return Arrays.asList(
                Author.builder().id(1L).name("1").build(),
                Author.builder().id(2L).name("2").build()
        );
    }
}
