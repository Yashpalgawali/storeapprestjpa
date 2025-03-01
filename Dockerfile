FROM openjdk:21-ea-31-jdk-slim
EXPOSE 4848
COPY  target/*.jar storeapprest.jar
ENTRYPOINT [ "java" , "-jar", "/storeapprest.jar"]