FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} checkout-service.jar
ENTRYPOINT ["java", "-jar", "/checkout-service.jar"]
EXPOSE 8083