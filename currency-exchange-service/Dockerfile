FROM fabric8/java-alpine-openjdk8-jdk
ADD target/*.jar currency-exchange.jar
EXPOSE 8000
RUN touch /currency-exchange.jar
ENTRYPOINT ["java","-jar","/currency-exchange.jar"]