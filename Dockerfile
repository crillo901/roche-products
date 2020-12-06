FROM adoptopenjdk/openjdk11:jdk-11.0.9_11-alpine

EXPOSE 8080

ARG JAR_FILE=build/libs/roche-products-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "--enable-preview", "-jar", "/app.jar"]