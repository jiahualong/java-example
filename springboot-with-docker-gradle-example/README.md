
# Springboot Docker 示例 

本resp实践了 `https://spring.io/guides/gs/spring-boot-docker`

## springboot编译并运行

如果不使用docker的话

```bash
gradle clean build && java -jar build/libs/springboot-with-docker-gradle-example-0.0.1-SNAPSHOT.jar
```

## springboot编译并构建docker镜像

编译后并使用`Dockfile`构建镜像

```bash
gradle build docker
```

构建后可以查看构建镜像

```bash
docker images
```

```txt
REPOSITORY                          TAG                 
cc.stan.example/gradle-example      latest
```

运行镜像

```bash
docker run -p 8080:8080 -t springio/gs-spring-boot-docker
```

指定配置文件运行

```bash
$ docker run -e "SPRING_PROFILES_ACTIVE=prod" -p 8080:8080 -t springio/gs-spring-boot-docker

- or -

$ docker run -e "SPRING_PROFILES_ACTIVE=dev" -p 8080:8080 -t springio/gs-spring-boot-docker
```

## Actuator状态信息

* http://localhost:8080/actuator
* http://localhost:8080/actuator/health
* http://localhost:8080/actuator/info

