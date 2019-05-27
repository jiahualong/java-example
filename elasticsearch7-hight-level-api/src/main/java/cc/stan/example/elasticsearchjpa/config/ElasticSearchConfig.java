package cc.stan.example.elasticsearchjpa.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfig {

    @Value("${elasticsearch.hostName}")
    private String hostName;
    @Value("${elasticsearch.rest-port}")
    private Integer restPort;
    @Value("${elasticsearch.index}")
    private String index;
    @Value("${elasticsearch.type}")
    private String type;


    @Bean
    public RestHighLevelClient restHighLevelClient() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost(hostName, restPort, "http")));
        return client;
    }


}
