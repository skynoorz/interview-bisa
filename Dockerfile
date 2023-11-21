FROM openjdk:17-jdk
LABEL maintainer="Ronald Guarachi <raguarachi0@gmail.com>"

WORKDIR /app
COPY target/interview-1.0.0-SNAPSHOT.war /app/interview-1.0.0-SNAPSHOT.war
EXPOSE 8080
CMD ["java", "-jar", "interview-1.0.0-SNAPSHOT.war"]
