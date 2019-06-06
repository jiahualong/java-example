package cc.stan.example.mongodb.mongodbexample.dbref;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, Long> {
}
