package cc.stan.example.elasticsearchjpa.pojo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;


@Data
@Accessors(chain = true)
@Document(indexName = "blog")
public class Blog {

    @Id
    private Long Id;
    private String title;
    private String content;
    private LocalDateTime create;

}
