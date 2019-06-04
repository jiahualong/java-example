package cc.stan.example.mongodb.mongodbexample.pojo;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Accessors(chain = true)
@Document("car")
public class Car {
    @Id
    private CarId id;
    private String name;

}
