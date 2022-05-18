Example of usage SpringBoot and SpringDataRest.

### Development:

Swagger UI: http://localhost:8080 (81)/swagger-ui/index.html

Docker:
- Package JAR: `.\mvnw clean package`
- Deploy: `docker compose up`

Notes:
- In modern IDEA to activate Spring profile write: `spring.profiles.active=dev`
- To attach service container terminal: `docker exec -ti employee-dep-service bin/sh`
- Redeploy (set IDEA run conf): `.\mvnw clean package | docker rmi employeedepservice_app -f | docker compose up`
- Logs are allowed in separate volume

### Project structure:
```
|-- config                 - global app config and components classes
|-- domain                 - domain layer (repositories)
|   |-- entity             - entities
|-- kafka
|   |-- consumer           - kafka consumers, should be separate module
|-- middleware             - business layer and request middleware
|   |-- messaging          - message brokers
|-- rest
|   |-- service            - REST controllers
|-- soap
|   |-- calculator         - public SOAP calculator client
|   |   |-- schema         - autogenerated schema 
|-- utils
|   |-- convert            - converters
```

#### Configurations inside 'resources':
- *db/changelog/db.changelog-master.yaml* - configuration for Liquidbase
- *application.properties*                - Spring Boot configs
- *calculator.wsdl*                       - public calculator SOAP service definition
- *logback-spring.xml*                    - Logback logging config
