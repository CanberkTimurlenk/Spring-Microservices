FROM openjdk:17-slim
WORKDIR /app
EXPOSE 8080
ARG JAR_FILE=target/ShipmentService-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /app/ShipmentService.jar
LABEL authors="Canberk"
ENTRYPOINT ["java","-jar","/app/ShipmentService.jar"]