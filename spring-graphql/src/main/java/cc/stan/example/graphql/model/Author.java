package cc.stan.example.graphql.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
public class Author {

    private Long id;
    private String name;

}
