FROM openjdk:20-jdk-slim AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN mvn clean package -DskipTests

FROM openjdk:20-jdk-slim
WORKDIR order_line
COPY ./target/order_line-0.0.1-SNAPSHOT.jar order_line.jar
ENTRYPOINT ["java", "-jar", "order_line.jar"]