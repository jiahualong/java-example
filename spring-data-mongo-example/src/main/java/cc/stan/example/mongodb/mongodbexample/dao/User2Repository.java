package cc.stan.example.mongodb.mongodbexample.dao;

import cc.stan.example.mongodb.mongodbexample.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.scheduling.annotation.Async;

import java.util.Optional;

public interface User2Repository extends MongoRepository<User, String> {
    Optional<User> findByIdIs(String id);

    Page<User> findAll(Pageable pageable);

//    @Async
//    Optional<User> findOneByUserName(String id);
}
