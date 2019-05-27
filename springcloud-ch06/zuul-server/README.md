# springcloud-zuul-server 

* springboot2.x 

访问eureka-server     http://localhost:8761/
访问product-server    http://127.0.0.1:8080/product/get?sku=1
通过zuul访问product   http://127.0.0.1:5555/product-server/product/get?sku=1
通过zuul路由映射访问   http://127.0.0.1:5555/ps/product/get?sku=1
查看zuul路由          http://127.0.0.1:5555/actuator/routes
配置了/api 后之后     http://127.0.0.1:5555/api/ps/product/get?sku=1
