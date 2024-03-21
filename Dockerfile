FROM openjdk:17-alpine
WORKDIR /app
COPY target/agenda-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "./agenda-0.0.1-SNAPSHOT.jar"]