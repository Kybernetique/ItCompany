FROM bellsoft/liberica-openjdk-alpine:17

WORKDIR /opt/web

COPY . /opt/web

EXPOSE 8080

RUN ["sh", "gradlew", "build"]

CMD ["java", "-jar", "/opt/web/build/libs/sbapp-0.0.1-SNAPSHOT.jar"]