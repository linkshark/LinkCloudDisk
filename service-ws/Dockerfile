FROM hub.c.163.com/library/java:latest
VOLUME /tmp
ADD target/service-ws-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]