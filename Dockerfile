FROM bellsoft/liberica-openjdk-alpine:17

WORKDIR /opt/web

COPY . /opt/web

EXPOSE 8080

#ENTRYPOINT ["java", "-jar", "build/libs/sbapp-0.0.1-SNAPSHOT.jar"]

CMD ["sh", "gradlew", "test"]