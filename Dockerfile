# Stage 1: Build the Spring Boot application
FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Create the final image with the built JAR file
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/amenal-back-end-hosted-0.0.1-SNAPSHOT.jar amenal-back-end-hosted.jar

# Expose port 8080 for the Spring Boot application
EXPOSE 8080

# Define environment variables for PostgreSQL
ENV POSTGRES_DB amenaldb
ENV POSTGRES_PASSWORD URAEFt6R9SEw4GFpd70UfkRxmHpnOl5Z
ENV POSTGRES_USER amenaldb_user

# Install PostgreSQL
RUN apt-get update && apt-get install -y postgresql

# Expose port 5432 for PostgreSQL
EXPOSE 5432

# Start PostgreSQL service
CMD service postgresql start && java -jar amenal-back-end-hosted.jar
