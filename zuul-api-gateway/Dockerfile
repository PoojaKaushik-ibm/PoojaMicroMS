FROM fabric8/java-alpine-openjdk8-jdk
ADD target/*.jar zuul-api.jar
EXPOSE 8765
RUN touch /zuul-api.jar
ENTRYPOINT ["java","-jar","/zuul-api.jar"]
