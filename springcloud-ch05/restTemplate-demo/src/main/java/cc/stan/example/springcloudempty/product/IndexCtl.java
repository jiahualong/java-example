package cc.stan.example.springcloudempty.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexCtl {

    private ProductRestTemplateClient productRestTemplateClient;

    @Autowired
    public IndexCtl(ProductRestTemplateClient productRestTemplateClient) {
        this.productRestTemplateClient = productRestTemplateClient;
    }

    @GetMapping("/")
    public String index() {
        return "restTemplate demo";
    }

    @GetMapping("/get")
    public Product get(String sku) {
        return productRestTemplateClient.findBySku(sku);
    }

    @GetMapping("/getII")
    public Product getII(String sku) {
        return productRestTemplateClient.findBySkuII(sku);
    }

    @GetMapping("/getIII")
    public Product getIII(String sku) {
        return productRestTemplateClient.findBySkuIII(sku);
    }
}
