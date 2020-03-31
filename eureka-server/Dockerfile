FROM hub.c.163.com/library/java:latest
VOLUME /tmp
ADD eureka-server.jar app.jar
# 开放1000端口
EXPOSE 1000
ENTRYPOINT ["java","-jar","/app.jar"]