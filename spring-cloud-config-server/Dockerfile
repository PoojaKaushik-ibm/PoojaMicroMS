FROM fabric8/java-alpine-openjdk8-jdk
ADD target/*.jar cloud-config.jar
EXPOSE 8888
RUN touch /cloud-config.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/cloud-config.jar"]