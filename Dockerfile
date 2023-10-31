FROM bellsoft/liberica-openjdk-alpine:17

WORKDIR /opt/web

COPY . /opt/web

EXPOSE 8080

RUN ["sh", "gradlew", "test"]