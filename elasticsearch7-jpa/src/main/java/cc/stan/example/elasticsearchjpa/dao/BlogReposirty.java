package cc.stan.example.elasticsearchjpa.dao;

import cc.stan.example.elasticsearchjpa.pojo.Blog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BlogReposirty extends ElasticsearchRepository<Blog, Long> {
}
