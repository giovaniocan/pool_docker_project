# instalando java
FROM openjdk:17-jdk

#copiando o jar para dentro do container com o nome app.jar
COPY target/api-0.0.1-SNAPSHOT.jar /app/app.jar

CMD ["java", "-jar", "/app/app.jar"]