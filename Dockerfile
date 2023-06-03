FROM maven:3.8.6-eclipse-temurin-11-alpine
WORKDIR /test
COPY src /test/src
COPY pom.xml /test
COPY testng.xml /test
COPY reports /test/reports
COPY logs /test/logs
COPY testdata /test/testdata
RUN mvn clean compile install -Dmaven.test.skip=true