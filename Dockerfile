FROM openjdk:17
ADD /target/KinoZOV-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
