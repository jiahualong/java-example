package cc.stan.example.mongodb.mongodbexample;

import cc.stan.example.mongodb.mongodbexample.dao.UserRepository;
import cc.stan.example.mongodb.mongodbexample.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void addUser() {

        User userA = new User().setId("1").setUserName("A").setPassword("password");
        User userB = new User().setId("2").setUserName("B").setPassword("password");
        User userC = new User().setId("3").setUserName("A").setPassword("password");

        // save 相当于 insert or update
        userRepository.save(userA);
        userRepository.save(userB);
        userRepository.save(userC);

        // insert插入时id不能相同
        User userD = new User().setId("4").setUserName("D").setPassword("password");
        userRepository.insert(userD);
    }

    @Test
    public void findFuser() {
        log.info("{}", userRepository.findByIdIs("1"));
        log.info("{}", userRepository.findByUserName("A"));
    }

}
