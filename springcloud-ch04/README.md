
# 第4章练习

## config-server

http://localhost:7000/ch03jpa/dev


## eureka-server

http://localhost:8761/app/appID
http://localhost:8761/


## 示例


| project | note |
|:---:|:---:|
| `eureka-server`       | 服务注册 |
| `config-server`       | config配置 |
| `product-server`      | 在数据库中查东西并返回 |
|                       | 调用 product-server |
| `discoveryClient-demo`| 使用`DiscoveryClient`调用 `product` |
| `netflixFeign-demo`   | 使用`netflixFeign`调用 `product` |
| `restTemplate-demo`   | 使用`Feign`调用`product` |

