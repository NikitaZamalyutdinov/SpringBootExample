Example of usage SpringBoot and SpringDataRest.

Swagger UI: http://localhost:8080/swagger-ui/index.html

Docker:
- Package JAR: `.\mvnw clean package`
- Deploy: `docker compose up`

Development:
- In modern IDEA to activate Spring profile write: `spring.profiles.active=dev`
- To attach service container terminal: `docker exec -ti employee-dep-service bin/sh`
- Redeploy (set IDEA run conf): `.\mvnw clean package | docker rmi employeedepservice_app -f | docker compose up`
- Logs are allowed in separate volume