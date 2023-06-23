
FROM openjdk:17
COPY /target/com.mongotask.api-0.0.1-SNAPSHOT.jar mongotask.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "mongotask.jar"]

