FROM docker.io/eclipse-temurin:17-jdk-alpine as build

WORKDIR /app
COPY gradle/ gradle
COPY build.gradle gradlew settings.gradle ./
COPY src ./src

RUN ./gradlew javadoc
RUN ./gradlew clean build


FROM docker.io/eclipse-temurin:17-jdk
COPY --from=build /app/build/libs/uniquefinder-0.0.1-SNAPSHOT.jar /home/app.jar

RUN apt update && apt install -y systemd

ARG USER=default_user
RUN adduser ${USER}
USER ${USER}

ENV PORT=8080
ENTRYPOINT ["java", "-jar", "-Dserver.port=${PORT}", "/home/app.jar"]
