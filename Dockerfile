FROM openjdk:8
ADD target/cryptocurrencytracker-0.0.1-SNAPSHOT.jar cryptocurrencytracker.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/cryptocurrencytracker.jar"]