# Overview
This is a Spring Boot WebFlux application designed to fetch data from an API, transform it and return the unique links between router locations. 
The application is built using Java 17 and leverages Spring Boot’s WebFlux framework for building non-blocking, reactive web applications. In this case we are using RestClient to make the web calls as its a simple synchronous API call.

Additionally, it integrates with SpringDoc OpenAPI for API documentation.

### Prerequisites
- Java 17

### Swagger API Documentation
Note: requires app to be running locally. See rest of README for more info.

URL: http://localhost:8080/apidocs

## Build the application
Full build with tests using the build.gradle build file without tests
```bash
./gradlew clean build
```

## Run the application
Full build with tests using the build.gradle build file without tests
```bash
./gradlew bootRun
```

or run using the JAR file
```bash
cd build/libs
java -jar app.jar
``` 
## Using the functionality
The app runs the requested functionality on start up. You will notice the output in the console.

To trigger the functionality, run the app as described.

Then from POSTMAN or any browser trigger this URL

http://localhost:8080/api/v1/service/routerLocations

The response will be the requested output of the exercise.

It outputs a list of unique links between routers, based on the data received from the API endpoint.

## Tests
There is basic unit tests written for the base functionality of this app.
Jacoco is a code coverage library used in this app which measures how much of the code is executed during tests. It helps identify untested parts of your codebase, ensuring higher code quality and reliability.

### Run tests
```bash
./gradlew test jacocoTestReport
```
A nice HTML report is created which can be access from build/jacocoHTML/index.html

Some aspects of the code have been left untested (such as the custom exception handling code) which will be visible in this report.
