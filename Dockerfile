FROM docker.io/eclipse-temurin:17-jdk-alpine

WORKDIR /app
 
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x ./mvnw
RUN ./mvnw dependency:go-offline
 
COPY src ./src
RUN  ./mvnw clean package

RUN cp target/*.jar app.jar

ARG USER=default_user
RUN adduser -D ${USER}
USER ${USER}

ENV PORT=8080
ENTRYPOINT ["java", "-jar", "-Dserver.port=${PORT}", "app.jar"]
