FROM gradle:8.14.3-jdk21-alpine AS build

WORKDIR /app

# Copy Gradle files first (for caching)
COPY build.gradle settings.gradle ./
COPY gradle ./gradle


RUN gradle dependencies --no-daemon || true


COPY src ./src


RUN gradle clean bootJar --no-daemon -x test

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app


RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
