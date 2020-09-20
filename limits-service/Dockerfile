FROM fabric8/java-alpine-openjdk8-jdk
ADD target/*.jar limits.jar
EXPOSE 8180
RUN touch /limits.jar
ENTRYPOINT ["java","-jar","/limits.jar"]