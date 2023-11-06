FROM bellsoft/liberica-openjdk-alpine:17

WORKDIR /opt/web

COPY . /opt/web

EXPOSE 8080

CMD ["sh", "gradlew", "test"]

# ENTRYPOINT ["java", "-jar", "build/libs/sbapp-0.0.1-SNAPSHOT.jar"]

