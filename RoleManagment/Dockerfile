FROM openjdk:17-jdk-slim
WORKDIR /RoleManagement
COPY target/RoleManagment-0.0.1-SNAPSHOT.jar /RoleManagement/role-management.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/RoleManagement/role-management.jar"]