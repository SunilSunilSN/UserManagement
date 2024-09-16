FROM openjdk:17-jdk-slim
WORKDIR /JournalManagement
COPY target/JournalManagment-0.0.1-SNAPSHOT.jar /JournalManagement/journal-management.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/JournalManagement/journal-management.jar"]