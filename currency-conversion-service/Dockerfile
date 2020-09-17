FROM fabric8/java-alpine-openjdk8-jdk
ADD target/*.jar currency-conversion.jar
EXPOSE 8100
RUN touch /currency-conversion.jar
ENTRYPOINT ["java","-Djava.security.eDockerfilegd=file:/dev/./urandom","-jar","/currency-conversion.jar"]