# syntax=docker/dockerfile:1

FROM openjdk:18-alpine
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
