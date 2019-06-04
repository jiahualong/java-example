package cc.stan.example.mongodb.mongodbexample.dao;

import cc.stan.example.mongodb.mongodbexample.pojo.Car;
import cc.stan.example.mongodb.mongodbexample.pojo.CarId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CarRepository extends MongoRepository<Car, CarId> {
}
