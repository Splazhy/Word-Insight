# Use an official OpenJDK 17 image as the base image
# FROM adoptopenjdk:17-jre-hotspot
FROM openjdk:22-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Install Maven
RUN apt-get update && \
    apt-get install -y maven
    
# Copy the Maven project's POM file and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the entire Maven project (excluding the target directory) into the container
COPY src src

# Build the Java application using Maven
RUN mvn package

# Define the command to run your Java application
CMD ["java", "-jar", "target/word-insight-1.0.0.jar"]
