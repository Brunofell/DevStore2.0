FROM openjdk:17-jdk-alpine
MAINTAINER devStore.com
COPY target/DevStore-0.0.1-SNAPSHOT.jar DevStore.jar
ENTRYPOINT ["java","-jar","/DevStore.jar"]