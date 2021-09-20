## Java Tech screening casus

### Starting the API

- Start the project through docker with the following command: ```docker-compose up```
- Start the API in development: ``./gradlew bootRun``
- Start locally with JAR (add '-x test' to the gradle build command to exclude tests):  
``./gradlew build && java -jar build/libs/{JAR_NAME}.jar``

### Requirements

- PostgreSQL database (v9.6 or higher recommended)

### Database

Please create an empty database and enter credentials in the application.properties,  
found in the resources directory. 
