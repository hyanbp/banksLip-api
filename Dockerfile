FROM openjdk:8-jdk-slim

ADD target/*.jar app.jar


ENTRYPOINT ["java", "-jar" , "/app.jar"]