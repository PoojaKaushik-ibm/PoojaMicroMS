FROM fabric8/java-alpine-openjdk8-jdk
ADD target/*.jar zuul-api.jar
EXPOSE 8888
RUN touch /zuul-api.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/zuul-api.jar"]