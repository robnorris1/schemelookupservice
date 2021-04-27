FROM openjdk:8-jdk-alpine
MAINTAINER robertn
COPY build/libs/schemelookupservice-0.0.2-SNAPSHOT.jar schemelookupservice-0.0.2-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/schemelookupservice-0.0.2-SNAPSHOT.jar"]