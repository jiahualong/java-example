package cc.stan.example.mongodb.mongodbexample.deref;

import cc.stan.example.mongodb.mongodbexample.dbref.EmailAddress;
import cc.stan.example.mongodb.mongodbexample.dbref.EmailAddressRepository;
import cc.stan.example.mongodb.mongodbexample.dbref.Person;
import cc.stan.example.mongodb.mongodbexample.dbref.PersonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbRefTest {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private EmailAddressRepository emailAddressRepository;

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

    @Test
    public void deleteAll() {
        personRepository.deleteAll();
    }

    @Test
    public void updateEmailAddress() {
        System.out.println(emailAddressRepository.findById(11L));
        Optional<EmailAddress> emailAddress = emailAddressRepository.findById(11L);
        if (emailAddress.isPresent()) {
            EmailAddress address = emailAddress.get();
            address.setValue("udpat@aaa.com");
            emailAddressRepository.save(address);
        }
        System.out.println(personRepository.findById(1L));
    }

    //https://www.baeldung.com/cascading-with-dbref-and-lifecycle-events-in-spring-data-mongodb
    //TODO: A Generic Cascade Implementation

}
