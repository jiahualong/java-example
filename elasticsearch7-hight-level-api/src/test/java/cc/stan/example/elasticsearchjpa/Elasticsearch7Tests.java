package cc.stan.example.elasticsearchjpa;

import cc.stan.example.elasticsearchjpa.pojo.Blog;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Elasticsearch7Tests {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void contextLoads() {


    }

    @Test
    public void indexRequestTest() throws IOException {
        IndexRequest indexRequest = new IndexRequest("blog");
        indexRequest.id("1").type("_doc");
        String json = new Blog().setId(1L)
                .setTitle("第一条微博")
                .setContent("第一条微博第一条微博第一条微博第一条微博第一条微博")
                .toJson();
        System.out.println(json);

        indexRequest.source(json, XContentType.JSON);

        restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);


    }
}
