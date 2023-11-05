FROM docker.io/eclipse-temurin:17-jdk-alpine
COPY target/*.jar app.jar
ENV PORT=8080
ENTRYPOINT ["java", "-jar", "-Dserver.port=${PORT}", "app.jar"]
