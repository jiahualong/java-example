package cc.stan.example.mongodb.mongodbexample.pojo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Accessors(chain = true)
@Document(collection = "user")
public class User {
    @Id
    private String id;
    @Indexed
    private String userName;
    private String password;

}
