package cc.stan.example.springcloudempty.ctl;

import cc.stan.example.springcloudempty.product.Product;
import cc.stan.example.springcloudempty.product.ProductDiscoveryClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class IndexCtl {

    private ProductDiscoveryClient client;

    @Autowired
    public IndexCtl(ProductDiscoveryClient client) {
        this.client = client;
    }

    @GetMapping("/")
    public String index() {
        return "ctl discoveryclient demo";
    }

    @GetMapping("/get")
    public Optional<Product> get(@RequestParam(defaultValue = "1") String sku) {
        return client.findBySku(sku);
    }
}
