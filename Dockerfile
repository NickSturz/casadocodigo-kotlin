FROM maven:3.6.3-jdk-11 AS builder
COPY /src /src
COPY pom.xml .
RUN mvn -f pom.xml clean package

FROM openjdk:11
COPY --from=builder /target/casadocodigo-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "casadocodigo-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080

