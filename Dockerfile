#Start with a base image containing Java runtime
FROM openjdk:17-jdk-alpine

#Information around who maintains the image
LABEL "org.opencontainers.image.authors"="banking-ms.com"

#Setting working directory
WORKDIR /app

# Add the application's jar to the image
COPY target/Accounts-0.0.1-SNAPSHOT.jar app.jar

# execute the application
ENTRYPOINT  ["java", "-jar", "app.jar"]



