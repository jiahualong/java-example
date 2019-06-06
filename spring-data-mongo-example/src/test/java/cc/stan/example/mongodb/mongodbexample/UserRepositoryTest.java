package cc.stan.example.mongodb.mongodbexample;

import cc.stan.example.mongodb.mongodbexample.dao.UserRepository;
import cc.stan.example.mongodb.mongodbexample.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

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

    @Test
    public void findUseQuery() {
        User user = mongoTemplate.findOne(
                Query.query(Criteria.where("userName").is("A")), User.class);
        user.setUserName("Jim");
        userRepository.save(user);
    }

    @Test
    public void findByCriteria() {
        User user = mongoTemplate.findOne(
                Query.query(Criteria.where("userName").is("A")),
                User.class);

        user.setPassword("A password");
        mongoTemplate.save(user);
        System.out.println(user);
    }

    @Test
    public void updateFirst() {
        //如果找到2个的话，更新第一个
        Query query = new Query().addCriteria(Criteria.where("userName").is("A"));
        Update update = new Update().set("userName", "A name");
        mongoTemplate.updateFirst(query, update, User.class);
    }

    @Test
    public void updateMulti() {
        //找到的所有的都进行更新
        Query query = new Query().addCriteria(Criteria.where("userName").is("A"));
        Update update = new Update().set("userName", "hello");
        mongoTemplate.updateMulti(query, update, User.class);
    }

    @Test
    public void findAndModify() {
        //更新并返回更新前的信息,(测试中只更新第一个)
        Query query = new Query().addCriteria(Criteria.where("userName").is("hello"));
        Update update = new Update().set("userName", "A");
        System.out.println(mongoTemplate.findAndModify(query, update, User.class));
    }

    @Test
    public void upsert() {
        //find and modity else create
        Query query = new Query().addCriteria(Criteria.where("userName").is("Jim"));
        Update update = new Update().set("userName", "Liu");
        System.out.println(mongoTemplate.upsert(query, update, User.class));
    }


    @Test
    public void remove() {
        mongoTemplate.remove(userRepository.findOneByUserName("Liu"), "user");
    }

    @Test
    public void deleteUseRepository() {
        //必须设置id
        User user = new User().setUserName("hello").setId("1");
        userRepository.delete(user);
    }

    @Test
    public void findOne() {
        User user = new User().setId("2");
        //NOT WORK
        assertTrue(userRepository.existsById(user.getId()));
        assertTrue(userRepository.findById(user.getId()).isPresent());
    }

    @Test
    public void findAllWithSort() {
        System.out.println(
                userRepository.findAll(new Sort(Sort.Direction.ASC, "userName"))
        );
    }

    @Test
    public void findWithPageable() {
        Pageable pageable = PageRequest.of(0, 2);
        Page<User> page = userRepository.findAll(pageable);
        List<User> userList = page.getContent();
        System.out.println(userList);
    }

    @Test
    public void idTest() {

    }

    //TODO: 即将要做的 https://www.baeldung.com/spring-data-mongodb-tutorial
    // 7 Annotations

}
