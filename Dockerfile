FROM openjdk:17-jdk

ADD build/libs/microservices-0.0.1-SNAPSHOT.jar/microservices.jar

ENTRYPOINT ["java", "-jar", "microservices.jar"]