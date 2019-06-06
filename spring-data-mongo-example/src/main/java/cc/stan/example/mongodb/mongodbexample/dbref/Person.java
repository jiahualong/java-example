package cc.stan.example.mongodb.mongodbexample.dbref;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document
public class Person {
    @Id
    private Long id;
    private String name;
    @DBRef
    private EmailAddress emailAddress;
}
