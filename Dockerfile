# Use an image with Maven and OpenJDK
FROM maven:3.8.4-openjdk
# Set working directory
WORKDIR /usr/src/fhirapp
# Copy all files into the container
COPY . /usr/src/fhirapp  
# Ensure permissions are correct for the /usr/src/fhirapp/target directory
RUN chmod -R 777 /usr/src/fhirapp/target
# Run Maven to clean, package, and execute the tests                   
RUN mvn clean test
# By default, we'll run Maven test in the container and exit
CMD ["mvn", "clean", "test"]
#CMD ["mvn", "cucumber:run"]

#CMD ["java", "-jar", "target/fhirgooglecloudapi-1.0-SNAPSHOT.jar"]