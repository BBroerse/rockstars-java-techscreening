# Start a gradle image, copy src into the container
# Compile the code and run tests (with gradle build)
# discrd the image with all compiled code
# Start again with the openjdk image and copy the JAR file only

#FROM gradle:7-jdk8 AS build
#COPY --chown=gradle:gradle . /home/gradle/src
#WORKDIR /home/gradle/src
#RUN gradle build --no-daemon

FROM openjdk:16-jdk-alpine
VOLUME /tmp
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]