package cc.stan.example.graphql.resolver;

import cc.stan.example.graphql.model.Author;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {

    public List<Author> queryAuthor() {
        return Collections.emptyList();
    }
}
