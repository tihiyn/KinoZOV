### Dockerized app with the basic Spring Security configuration
To get started with the application, run from the project directory:
```shell
mvn clean install -DskipTests
docker-compose up -d --build
```
You can get a list of available containers and explore their logs using:
```shell
docker ps -a
docker logs <container_name>
```
To stop the application, run:
```shell
docker-compose down
```
