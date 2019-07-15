package cc.stan.example.graphql.resolver;

import cc.stan.example.graphql.model.Book;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class BookQuery implements GraphQLQueryResolver {

    public List<Book> queryBook() {
        return Arrays.asList(
                Book.builder().id(1L).name("book1")
                        //.authors(Arrays.asList(Author.builder().id(21L).name("hello").build(), Author.builder().id(22L).name("world").build()))
                        .build(),
                Book.builder().id(2L).name("book2")
                        //.authors(Arrays.asList( Author.builder().id(21L).name("hello").build(), Author.builder().id(22L).name("world").build()))
                        .build()
        );
    }

}
