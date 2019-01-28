FROM maven:3.5-jdk-8

COPY ./pom.xml ./pom.xml
RUN mvn dependency:go-offline -B
COPY ./src ./src
RUN mvn package

CMD ["java","-jar","./target/fillexc.jar"]
