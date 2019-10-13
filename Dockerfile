# Mvn Specific
# ---- Builder ----
FROM gradle:4.7.0-jdk8-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon 

# Java Specific
# ---- Runner ----
FROM openjdk:8-jre-alpine
COPY --from=builder /home/gradle/src/build/libs/*.jar /app/kotlin-application.jar
ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/kotlin-application.jar"]