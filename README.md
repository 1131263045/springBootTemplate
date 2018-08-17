SpringBootTemplate
==================


# 简介

随着框架使用的不断的更新，后面使用SpringBoot会多，这边准备构建一个SpringBoot项目使用的模版。
所谓模版，和之前一样，就是一个最简单的项目，包含所有最简单的空实现。

<br/>
<br/>

## 模版目的  
- 1、不熟悉SpringBoot项目的人，在学习了SpringBoot的基础部分之后，能根据这个模版快速上手
- 2、开发团队的项目结构和使用装备的统一，包括目录结构，使用的一些组件上面的统一
- 3、不想重复造轮子，每次新建一个项目很麻烦

<br/>
<br/>

## 目录说明  

- `src/main/java/`

存放java代码，其中SpringBootTemplateApplication为SpringBoot启动类

- `src/main/resources/`

application.yml 全局配置文件  
application-data.yml  数据源相关配置文件  
application-mvc.yml  页面mvc相关配置文件  
application-config.yml  需要引入的一些全局配置

application-dev.yml  开发环境配置    
application-prod.yml  生产环境配置

- `src/main/resources/static`

存放各种静态资源文件，如css，js，image等

- `src/main/resources/templates`

存放使用的页面文件，各种html等

- `src/main/resources/mapper`

存放mybatis的sql语句xml文件

<br/>
<br/>

## 数据结构

> 表结构定义写法如下

````sql
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_tab
-- ----------------------------
DROP TABLE IF EXISTS `user_tab`;
CREATE TABLE `user_tab` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `val` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;
````


## 注意事项

1. _项目启动前本机需要启动redis，否则会出现ERRROR_
2. _测试地址为http://127.0.0.1:8080/test，测试方法全部在UserController中_

<br/>
<br/>



## RedisUtil

> 新Redis操作工具类,暴露如下操作方法

| 方法名        | 入参                                      | 出参    |
| ------------- | ----------------------------------------- | ------- |
| setExpireTime | String key, long timeout                  | boolean |
| delete        | String key                                | void    |
| hasKey        | String key                                | boolean |
| set           | String key, Object value                  | void    |
| set           | String key, Object value, Long expireTime | void    |
| get           | String key                                | Object  |

`RedisUtil`文件路径于`src/main/java/com/linkinstars/springBootTemplate/util/RedisUtil.java`

<br/>
<br/>


## Dockerfile使用步骤   
1、使用gradle的bootRepackage进行打包   
2、Dockerfile目录下使用命令：docker build -t springboot:v1.0 .  
3、启动本地redis，并修改application-dev.yml中redis的IP地址为宿主机的IP地址如：192.168.1.111，mysql的IP地址同理   
4、使用命令：docker run --name springbootTemplate -d -p 8080:8080 springboot:v1.0   
5、直接访问测试地址即可  


### 注意事项

- `Dockerfile`中的`APP_NAME`对应`jar.baseName-jar.version`
- `Dockerfile`中的`APP_PORT`&`EXPOSE`根据项目情况填写

<br/>
<br/>