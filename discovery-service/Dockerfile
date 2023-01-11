FROM openjdk:21-slim-buster
ARG JAR_FILE=target/discovery-service.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","app.jar"]