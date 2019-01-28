FROM java:8
VOLUME /tmp
EXPOSE 8080
COPY ./target/fillexc-*.jar fillexc.jar
ENTRYPOINT ["java", "-jar", "/fillexc.jar"]
