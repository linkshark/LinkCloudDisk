# LinkCloudDisk
基于springcloud开发的分布式美剧订阅系统(含爬虫)
此项目为springcloud项目 并引入了Eureka作为服务注册发现的实现,feign 作为分布式系统中的负载均衡组件
同时引入Hystrix熔断机制,保证系统的高可用,预防单点故障引发的系统崩溃 
引入 Zuul网关 作为反向代理并配置登录拦截 

如何使用:
1:首先导入根目录下的sql文件建库建表(mysql)

2:开启eureka-server项目,开启eureka-server组件作为服务的注册

3:配置redis环境并将相关配置写入到application.yml中,开启service-regist,作为eureka-client向eureka-server注册自己的服务,并请求监管 
