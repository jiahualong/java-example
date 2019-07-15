package cc.stan.example.graphql.resolver;

import cc.stan.example.graphql.model.Author;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class AuthorQuery implements GraphQLQueryResolver {

    public List<Author> queryAuthor() {
        return Arrays.asList(
                Author.builder().id(1L).name("hello").build(),
                Author.builder().id(2L).name("world").build()
        );
    }

}
