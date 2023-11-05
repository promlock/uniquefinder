FROM docker.io/eclipse-temurin:17-jdk-alpine

WORKDIR /app
 
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
 
COPY src ./src
RUN  ./mvnw clean package

ENV PORT=8080
ENTRYPOINT ["java", "-jar", "-Dserver.port=${PORT}", "/app/target/uniquefinder-0.0.1-SNAPSHOT.jar"]
