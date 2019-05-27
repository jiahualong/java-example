package cc.stan.example.springcloudempty.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductRestTemplateClient {

    private RestTemplate restTemplate;

    @Autowired
    public ProductRestTemplateClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Product findBySku(String sku) {
        ResponseEntity<Product> restExchange =
                restTemplate.exchange("http://product-server/product/get?sku={sku}",
                        HttpMethod.GET, null, Product.class, sku);
        return restExchange.getBody();
    }

}
