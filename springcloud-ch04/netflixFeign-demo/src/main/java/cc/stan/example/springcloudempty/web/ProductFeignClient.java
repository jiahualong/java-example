package cc.stan.example.springcloudempty.web;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Component
@FeignClient("product-server")
public interface ProductFeignClient {

    @RequestMapping(method = GET,
            value = "/product/get")
    Product findBySku(@RequestParam("sku") String sku);

}
