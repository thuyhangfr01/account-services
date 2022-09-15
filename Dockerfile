FROM gradle:7.5-jdk8 as gradleimage
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle init && gradle build -x test

FROM openjdk:8-jdk-alpine as callvideoservices
EXPOSE 8080
RUN mkdir /app
COPY --from=gradleimage /home/gradle/src/build/libs/*.jar /app/
ENTRYPOINT ["java","-jar","/app/account-service-0.0.1-SNAPSHOT.jar"]