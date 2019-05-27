package cc.stan.example.elasticsearchjpa;

import cc.stan.example.elasticsearchjpa.dao.BlogReposirty;
import cc.stan.example.elasticsearchjpa.pojo.Blog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Elasticsearch7Tests {

    @Autowired
    private BlogReposirty blogReposirty;

    @Test
    public void contextLoads() {
        System.out.println("heloo");

        blogReposirty.save(new Blog()
                .setId(1L)
                .setTitle("第一条微博")
                .setContent("第一条微博第一条微博第一条微博第一条微博第一条微博")
                .setCreate(LocalDateTime.now()));

    }

}
