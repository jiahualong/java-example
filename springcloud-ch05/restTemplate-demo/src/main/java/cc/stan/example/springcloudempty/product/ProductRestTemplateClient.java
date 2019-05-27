package cc.stan.example.springcloudempty.product;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Component
public class ProductRestTemplateClient {

    private RestTemplate restTemplate;

    @Autowired
    public ProductRestTemplateClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //@HystrixCommand //默认配置
    //@HystrixCommand(commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "12000")}) //设置默认时间12秒
    @HystrixCommand(fallbackMethod = "buildFallbackLicenseList") //配置后备方案
    public Product findBySku(String sku) {
        randomlyRunLong();
        ResponseEntity<Product> restExchange =
                restTemplate.exchange("http://product-server/product/get?sku={sku}",
                        HttpMethod.GET, null, Product.class, sku);
        return restExchange.getBody();
    }

    //后备处理方法
    private Product buildFallbackLicenseList(String sku) {
        return new Product().setName("后备方案Name")
                .setPrice(0.0)
                .setSku("000");
    }

    private void randomlyRunLong() {
        Random random = new Random();
        if (random.nextInt((3 - 1) + 1) + 1 == 3)
            sleep();
    }

    private void sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //定义threadPool行为
    @HystrixCommand(
            fallbackMethod = "buildFallbackLicenseList",
            threadPoolKey = "findBySkuIIThreadPool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"), //线程池最大量
                    @HystrixProperty(name = "maxQueueSize", value = "10") //线程池前的队列
            })
    public Product findBySkuII(String sku) {
        ResponseEntity<Product> restExchange =
                restTemplate.exchange("http://product-server/product/get?sku={sku}",
                        HttpMethod.GET, null, Product.class, sku);
        return restExchange.getBody();
    }

    //微调Hystrix

    @HystrixCommand(
            fallbackMethod = "buildFallbackLicenseList",
            threadPoolKey = "findBySkuIIThreadPool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"), //线程池最大量
                    @HystrixProperty(name = "maxQueueSize", value = "10") //线程池前的队列
            },
            commandProperties = {
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), //跳闸之前连续调用数量
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75"), //必须达到的调用失败百分比
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "7000"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "15000"), //窗口大小
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "5") //桶数量
            }
    )
    public Product findBySkuIII(String sku) {
        ResponseEntity<Product> restExchange =
                restTemplate.exchange("http://product-server/product/get?sku={sku}",
                        HttpMethod.GET, null, Product.class, sku);
        return restExchange.getBody();
    }

}
