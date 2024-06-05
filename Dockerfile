FROM maven:3.9.6-eclipse-temurin-21-jammy AS MAVEN_TOOL_CHAIN
WORKDIR /tmp/

COPY pom.xml /tmp/pom.xml
COPY checkstyle /tmp/checkstyle/
COPY lib /tmp/lib/
COPY lombok.config /tmp/lombok.config
RUN mvn dependency:resolve-plugins dependency:go-offline -B
COPY src /tmp/src/
RUN mvn package -B

FROM eclipse-temurin:21-jre-jammy
RUN mkdir /opt/app
COPY --from=MAVEN_TOOL_CHAIN /tmp/target/libridgebackend*.jar /opt/app/server.jar
EXPOSE 8080
CMD ["java", "-jar", "/opt/app/server.jar"]