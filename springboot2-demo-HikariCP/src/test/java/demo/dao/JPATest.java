package demo.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JPATest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void test() {
        User user = userRepository.findByUsernameIsAndPasswordIs("11111username", "1111pwd");
        System.out.println(user);
    }
}
