# syntax=docker/dockerfile:1

FROM bellsoft/liberica-openjdk-alpine:18-x86_64
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
