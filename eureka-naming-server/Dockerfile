FROM fabric8/java-alpine-openjdk8-jdk
ADD target/*.jar eureka.jar
EXPOSE 8761
RUN touch /eureka.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/eureka.jar"]