FROM openjdk:17-jdk-alpine
COPY build/libs/app.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Xmx640m", "-XX:MaxRAMPercentage=75.0", "app.jar" ]