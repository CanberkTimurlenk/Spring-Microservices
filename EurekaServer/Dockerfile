FROM openjdk:17-slim
WORKDIR /app
EXPOSE 8080
ARG JAR_FILE=target/EurekaServer-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /app/eurekaserver.jar
LABEL authors="Canberk"
ENTRYPOINT ["java","-jar","/app/eurekaserver.jar"]