package cc.stan.example.mongodb.mongodbexample.dao;

import cc.stan.example.mongodb.mongodbexample.pojo.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    User findByIdIs(String id);

    List<User> findByUserName(String userName);

    User findOneByUserName(String userName);

}
