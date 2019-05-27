package cc.stan.example.springcloudempty.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloCtl {

    private ProductFeignClient productFeignClient;

    @Autowired
    public HelloCtl(ProductFeignClient productFeignClient) {
        this.productFeignClient = productFeignClient;
    }

    @GetMapping("/")
    public String hello() {
        return "hello netflixFeign demo";
    }

    @GetMapping("/get")
    public Product get(String sku) {
        return productFeignClient.findBySku(sku);
    }

}
