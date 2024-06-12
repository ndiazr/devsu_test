FROM gradle:latest AS build

WORKDIR /app

COPY build.gradle settings.gradle /app/
COPY gradlew /app/
COPY gradle /app/gradle

COPY src /app/src

RUN ./gradlew clean build --no-daemon

FROM openjdk:21

WORKDIR /app

COPY --from=build /app/build/libs/*.jar /app/clientback.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/clientback.jar"]