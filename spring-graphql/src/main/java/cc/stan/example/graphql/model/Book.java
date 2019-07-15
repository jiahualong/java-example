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
    //authors属性从 BookResolver 获取, 不请求authors不获取
    private List<Author> authors;
}
