package cc.stan.example.springcloudempty.product;

import cc.stan.example.springcloudempty.product.Product;
import cc.stan.example.springcloudempty.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductCtl {

    private ProductService productService;

    @Autowired
    public ProductCtl(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/get")
    public Product get(String sku) {
        return productService.findBySku(sku);
    }

}
