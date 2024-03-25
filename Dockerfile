# Stage 1: Build the Spring Boot application
FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Create the final image with the built JAR file
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/amenal-backend-hosted-0.0.1-SNAPSHOT.jar amenal-backend-hosted.jar

# Expose port 8080
EXPOSE 8080


# Start the Spring Boot application
CMD ["java", "-jar", "amenal-backend-hosted.jar"]