FROM bellsoft/liberica-openjdk-alpine:17

WORKDIR /opt/web

COPY . /opt/web

EXPOSE 8080

#CMD ["sh", "gradlew", "test"]

RUN ["sh", "gradlew", "test"]
CMD ["sh", "gradlew", "bootRun"]
