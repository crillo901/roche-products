FROM adoptopenjdk/openjdk11:latest

EXPOSE 8080

ARG JAR_FILE=build/libs/roche-products-0.0.1-SNAPSHOT.jar

ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java", "--enable-preview", "-jar", "/app.jar"]