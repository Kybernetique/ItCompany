# it-company
docker stop web-project-container

docker rm web-project-container
docker rmi web-project

docker build -t=web-project .

docker run -itd -p 8090:8080 --name=web-project-container web-project

docker ps -a

docker exec -it web-project-container /bin/sh

docker start web-project-container

# CMD ["sh", "gradlew", "test"]

# CMD ["sh", "gradlew", "bootRun"]

# sbapp-0.0.1-SNAPSHOT.jar
