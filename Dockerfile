FROM openjdk:11
ADD target/foodOrderingSystem-1.0-SNAPSHOT-jar-with-dependencies.jar app.jar
ADD src/main/resources /resources
ENTRYPOINT ["java", "-jar","app.jar"]