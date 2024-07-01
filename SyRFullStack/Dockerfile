# Fase 1: Construcción de la aplicación
FROM maven:3.8.4-openjdk-17-slim AS build

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo pom.xml y descarga las dependencias necesarias
COPY pom.xml .
RUN mvn dependency:go-offline

# Copia el código fuente de la aplicación
COPY src ./src

# Empaqueta la aplicación Spring Boot como un JAR ejecutable
RUN mvn package -DskipTests

# Verifica si el archivo JAR fue generado
RUN ls -l target/

# Fase 2: Creación de una imagen más ligera solo con JRE
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el JAR generado desde la fase de construcción
COPY --from=build /app/target/SyRFullStack-0.0.1-SNAPSHOT.jar ./app.jar

# Exponer el puerto 8081 para la aplicación Spring Boot
EXPOSE 8081

# Define el comando a ejecutar cuando se inicie el contenedor
CMD ["java", "-jar", "app.jar"]