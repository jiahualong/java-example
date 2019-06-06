package cc.stan.example.mongodb.mongodbexample.dbref;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmailAddressRepository extends MongoRepository<EmailAddress, Long> {
}
