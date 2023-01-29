# Use an official Java runtime as the base image
FROM openjdk:11

# Set the working directory in the container to /app
WORKDIR /app

# Copy the jar file and dependencies into the container
COPY target/*.jar chatGPT-0.0.1-SNAPSHOT.jar

# Expose port 8080 to the host
EXPOSE 8080

# Start the application
CMD ["java", "-jar", "chatGPT-0.0.1-SNAPSHOT.jar"]
