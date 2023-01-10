FROM openjdk:21-slim-buster
COPY target/orders-service.jar app.jar
ENV JAVA_OPTS=""
EXPOSE 7071
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava-security.egd=file:/dev/./urandom -jar /app.jar"]
