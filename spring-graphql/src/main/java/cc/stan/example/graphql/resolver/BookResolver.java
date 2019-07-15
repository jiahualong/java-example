package cc.stan.example.graphql.resolver;

import cc.stan.example.graphql.model.Author;
import cc.stan.example.graphql.model.Book;
import com.coxautodev.graphql.tools.GraphQLResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 通过property获取数据
 * <p>
 * https://www.graphql-java-kickstart.com/tools/schema-definition/
 */
@Slf4j
@Component
public class BookResolver implements GraphQLResolver<Book> {

    public List<Author> authors(Book book) {
        log.info("获取authors, book,{}", book);
        return Arrays.asList(
                Author.builder().id(1L).name("auth 1").build(),
                Author.builder().id(2L).name("auth 2").build()
        );
    }
}
