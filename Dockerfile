FROM maven:3.8.4-openjdk-11
COPY . /usr/src/fhirapp
WORKDIR /usr/src/fhirapp
RUN mvn clean package
CMD ["java", "-jar", "target/fhirgooglecloudapi-1.0-SNAPSHOT.jar"]