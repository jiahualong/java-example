# springcloud-eureka-server 


NOTE: springboot2.x 

spring-cloud-starter-eureka-server 更名为 -> spring-cloud-starter-netflix-eureka-server


查看服务所有实例

http://localhost:8761/eureka/app/appId

http头 Accept application/json

查看服务实例 
http://localhost:8761/eureka/apps/product-server

```shell
curl --header 'Accept:application/json' http://localhost:8761/eureka/apps/product-server
```

