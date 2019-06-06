package cc.stan.example.mongodb.mongodbexample.deref;

import cc.stan.example.mongodb.mongodbexample.dbref.EmailAddress;
import cc.stan.example.mongodb.mongodbexample.dbref.Person;
import cc.stan.example.mongodb.mongodbexample.dbref.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbRefTest {

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void testSave1() {
        personRepository.save(
                new Person(1L, "John", new EmailAddress(11L, "aaa@aa.com"))
        );

    }

    @Test
    public void find() {
        System.out.println(personRepository.findById(1L));
    }
}
