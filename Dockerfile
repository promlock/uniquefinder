FROM docker.io/eclipse-temurin:17-jdk
RUN apt update && apt install -y systemd

WORKDIR /app

COPY gradle/ gradle
COPY build.gradle build.gradle
COPY gradlew/ gradlew
COPY settings.gradle settings.gradle

COPY src ./src
RUN ./gradlew javadoc

RUN ./gradlew clean build

RUN cp build/libs/uniquefinder-0.0.1-SNAPSHOT.jar app.jar

ARG USER=default_user
RUN adduser ${USER}
USER ${USER}

ENV PORT=8080
ENTRYPOINT ["java", "-jar", "-Dserver.port=${PORT}", "app.jar"]
