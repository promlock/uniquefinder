FROM docker.io/eclipse-temurin:17-jdk
RUN apt update && apt install -y systemd

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x ./mvnw
RUN ./mvnw dependency:go-offline

COPY src ./src
RUN ./mvnw javadoc:javadoc
RUN cp -a target/site/apidocs/. src/main/resources/static/

RUN  ./mvnw clean package

RUN cp target/*.jar app.jar

ARG USER=default_user
RUN adduser ${USER}
USER ${USER}

ENV PORT=8080
ENTRYPOINT ["java", "-jar", "-Dserver.port=${PORT}", "app.jar"]
