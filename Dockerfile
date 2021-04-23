FROM openjdk:8
ADD target/task-0.0.1-SNAPSHOT.jar task.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/task.jar"]