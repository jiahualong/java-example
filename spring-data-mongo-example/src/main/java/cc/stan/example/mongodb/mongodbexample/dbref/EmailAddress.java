package cc.stan.example.mongodb.mongodbexample.dbref;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document
public class EmailAddress {
    @Id
    private Long id;

    private String value;
}
