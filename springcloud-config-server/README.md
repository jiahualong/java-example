基于Localtion file 的配置放在config-store中
通过URL获取
http://localhost:7000/userservice/dev


# config 服务变量

ENCRYPT_KEY=IMSYMMETRIC


## 加密 dev

curl localhost:7000/encrypt -d dev

## 解密 

curl localhost:7000/decrypt -d 0e5f37c54ce27fad8a5fa0d928818eb3d9e44e320a3c8ab671fced2c921d9616

curl localhost:7000/decrypt -d 37076b2a944ee32a3e234f4f23c36814d960d2e4595b161c612f5560b893c3a5

## 这里有点问题
 encrypt.enabled: 为true或false时获取到的密码都被解密
