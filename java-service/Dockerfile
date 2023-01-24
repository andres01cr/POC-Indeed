FROM maven:3.8-jdk-11
RUN mkdir /java-service
COPY . /java-service
WORKDIR /java-service
RUN mvn clean package -DskipTests
CMD ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]