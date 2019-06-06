package cc.stan.example.mongodb.mongodbexample;

import cc.stan.example.mongodb.mongodbexample.dao.CarRepository;
import cc.stan.example.mongodb.mongodbexample.pojo.Car;
import cc.stan.example.mongodb.mongodbexample.pojo.CarId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Test
    public void test() {
        carRepository.save(new Car()
                .setId(new CarId(1L, "a"))
                .setName("Tesla")
        );
        assertNotNull(carRepository.findById(new CarId(1L, "a")));
    }
}
