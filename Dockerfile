# Use an official JDK image
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy build files
COPY . .

# Grant execution rights for Gradle
RUN chmod +x ./gradlew

# Build the application
RUN ./gradlew build --no-daemon

# Run the app
CMD ["java", "-jar", "build/libs/gladytt-0.0.1-SNAPSHOT.jar"]
