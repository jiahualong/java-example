package cc.stan.example.graphql.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Builder
@Accessors(chain = true)
public class Book {
    private Long id;
    private String name;
    private List<Author> authors;
}
