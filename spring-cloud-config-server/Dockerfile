FROM fabric8/java-alpine-openjdk8-jdk
ADD target/*.jar cloud-config.jar
EXPOSE 8888
RUN touch /cloud-config.jar
ENTRYPOINT ["java","-jar","/cloud-config.jar"]