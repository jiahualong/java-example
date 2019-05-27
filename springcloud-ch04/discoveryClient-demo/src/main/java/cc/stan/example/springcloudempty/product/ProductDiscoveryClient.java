package cc.stan.example.springcloudempty.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Component
public class ProductDiscoveryClient {

    private DiscoveryClient discoveryClient;

    @Autowired
    public ProductDiscoveryClient(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    public Optional<Product> findBySku(String sku) {

        RestTemplate restTemplate = new RestTemplate();
        List<ServiceInstance> instances = discoveryClient.getInstances("PRODUCT-SERVER");

        if (instances.size() == 0)
            return Optional.empty();

        String serviceUri = String.format("%s/product/get?sku=%s", instances.get(0).getUri().toString(), sku);

        ResponseEntity<Product> restExchange =
                restTemplate.exchange(
                        serviceUri,
                        HttpMethod.GET,
                        null,
                        Product.class,
                        sku
                );
        return Optional.of(restExchange.getBody());
    }

}
