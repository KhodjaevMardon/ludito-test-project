#
# Build stage
#
FROM maven:3.9.11-amazoncorretto-21 AS builder
COPY pom.xml /app/
COPY src /app/src
RUN --mount=type=cache,target=/root/.m2 mvn -f /app/pom.xml clean package -DskipTests

#
# Package stage
#
FROM amazoncorretto:21
COPY --from=builder /app/target/LuditoTestProject-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","app.jar"]